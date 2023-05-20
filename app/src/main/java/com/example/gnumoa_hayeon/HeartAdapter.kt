package com.example.gnumoa_hayeon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


class HeartAdapter() : RecyclerView.Adapter<HeartAdapter.HeartViewHolder>() {
    private val heartList: MutableList<Notice_list> = mutableListOf()
    private fun getContextPreview(context: List<String>): String {
        if (context.isNotEmpty()) {
            val fullText = context[0]
            return if (fullText.length > 70) "${fullText.substring(0, 70)}..." else fullText
        }
        return ""
    }

    private val changeHeartInfo = SharedDB.getInstance()
    private val heartEditor = changeHeartInfo.edit()
    private val allEntries: Map<String, *> = changeHeartInfo.all

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_list, parent, false)
        return HeartViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeartAdapter.HeartViewHolder, position: Int) {
        holder.major.text = heartList[position].major
        holder.category.text = heartList[position].category
        holder.title.text = heartList[position].title
        holder.context.text = getContextPreview(heartList[position].context!!)
//        heartList[position].heart.let { holder.heart.setImageResource(R.drawable.full_heart) }
//        holder.bind(heartList[position])



        // Firestore Timestamp 객체를 Date 객체로 변환
        val timestamp = heartList[position].createdAt as Timestamp
        val date = timestamp.toDate()

        // SimpleDateFormat을 사용하여 원하는 형식으로 포맷팅
        val sdf = SimpleDateFormat("yy / MM / dd", Locale.getDefault())
        holder.createdAt.text = sdf.format(date)

    }

    override fun getItemCount(): Int {
        return heartList.size
    }

    private var heartVal: Boolean?= null

    inner class HeartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val major: TextView = itemView.findViewById(R.id.tv_major) // 학과
        val category: TextView = itemView.findViewById(R.id.tv_category) //카테고리
        val title: TextView = itemView.findViewById(R.id.tv_title) // 제목
        val context: TextView = itemView.findViewById(R.id.tv_context) // 내용요약
        val createdAt: TextView = itemView.findViewById(R.id.tv_createdAt) // 날짜
        val heart: ImageButton = itemView.findViewById(R.id.img_heart) // 관심목록

        init {
            SharedDB.init(itemView.context)
        }

        fun heart_bind(heartValue: Boolean, key: String) {

            heart.setOnClickListener {
                if(heartValue) {
                    heart.setImageResource(R.drawable.full_heart)
                } else {
                    heart.setImageResource(R.drawable.empty_heart)
                    heartEditor.remove(key) // 데이터 삭제
                    heartEditor.apply()
                    Toast.makeText(itemView.context, "관심목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //역질렬화
    private fun deserializeData(serializedData: String): Notice_list {
        val gson = Gson()
        return gson.fromJson(serializedData, Notice_list::class.java)
    }

    init {
        for((_, value) in allEntries)  {
            val serializedData = value as String
            val item: Notice_list = deserializeData(serializedData)
            val key = item.major + "_" + item.title
            heartVal = item.heart
            heartList.add(item)
        }
        notifyDataSetChanged()
    }


}







