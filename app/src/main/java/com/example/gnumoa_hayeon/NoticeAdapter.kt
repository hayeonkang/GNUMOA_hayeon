package com.example.gnumoa_hayeon

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.view.View

// : -> 상속
class NoticeAdapter(val noticeList: ArrayList<Notice_list>) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    //공지화면이랑 공지리스트 병합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeAdapter.NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_list, parent, false) //view 생성
        return NoticeViewHolder(view) //뷰홀더에 뷰 전달
    }

    //화면에 전달
    override fun onBindViewHolder(holder: NoticeAdapter.NoticeViewHolder, position: Int) {
        holder.major.text = noticeList.get(position).major
        holder.category.text = noticeList.get(position).category
        holder.title.text = noticeList.get(position).title
        holder.context.text = noticeList.get(position).context
        holder.date.text = noticeList.get(position).date.toString() //문자열로 변환
        holder.heart.setImageResource(noticeList.get(position).heart)
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    //화면에 표시될 아이템 뷰 저장하는 객체
    inner class NoticeViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val major = itemView.findViewById<TextView>(R.id.tv_major) // 학과
        val category = itemView.findViewById<TextView>(R.id.tv_category) //카테고리
        val title = itemView.findViewById<TextView>(R.id.tv_title) // 제목
        val context = itemView.findViewById<TextView>(R.id.tv_context) // 내용요약
        val date = itemView.findViewById<TextView>(R.id.tv_date) // 날짜
        val heart = itemView.findViewById<ImageButton>(R.id.img_heart) // 관심공지
    }


}