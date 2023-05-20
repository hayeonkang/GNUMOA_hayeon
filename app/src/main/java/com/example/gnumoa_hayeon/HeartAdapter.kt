package com.example.gnumoa_hayeon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HeartAdapter(private val heartList: ArrayList<Notice_list>) : RecyclerView.Adapter<HeartAdapter.HeartViewHolder>() {

    private fun getContextPreview(context: List<String>): String {
        if (context.isNotEmpty()) {
            val fullText = context[0]
            return if (fullText.length > 70) "${fullText.substring(0, 70)}..." else fullText
        }
        return ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_list, parent, false)
        return HeartViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeartAdapter.HeartViewHolder, position: Int) {
        holder.major.text = heartList[position].major
        holder.category.text = heartList[position].category
        holder.title.text = heartList[position].title
        holder.context.text = getContextPreview(heartList[position].context!!)
        heartList[position].heart.let { holder.heart.setImageResource(R.drawable.empty_heart) }


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

    inner class HeartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val major: TextView = itemView.findViewById(R.id.tv_major) // 학과
        val category: TextView = itemView.findViewById(R.id.tv_category) //카테고리
        val title: TextView = itemView.findViewById(R.id.tv_title) // 제목
        val context: TextView = itemView.findViewById(R.id.tv_context) // 내용요약
        val createdAt: TextView = itemView.findViewById(R.id.tv_createdAt) // 날짜
        val heart: ImageButton = itemView.findViewById(R.id.img_heart) // 관심목록
    }


}