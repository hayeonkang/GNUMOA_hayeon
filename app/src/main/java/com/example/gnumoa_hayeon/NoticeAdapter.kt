package com.example.gnumoa_hayeon

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

// : -> 상속
// 1. Notice_list 데이터 클래스를 들고와서 ArrayList로 리스트화 시킨 것을 noticeList 변수에 넣음
// 2. 리사이클러뷰에 있는 어댑터 속성 가져오기
class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    val noticeList: ArrayList<Notice_list> = arrayListOf()

    init {
        val firestore = FirebaseFirestore.getInstance()

        firestore?.collection("inmun")?.document("dokmun")?.collection("기타")
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                noticeList.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(Notice_list::class.java)
                    noticeList.add(item!!)
                }
                notifyDataSetChanged()
            }
    }

    //onCreateViewHolder, onBindViewHolder, getItemCount() => view 홀더가 생성되는 곳
    //NoticeViewHolder => view 홀더 정의하는 곳

    //공지화면이랑 공지리스트 병합
    // 1. LayoutInflater를 통해 설계해둔 리스트를 view 변수안에 넣어서 생성
    // 2. NoticeViewHolder에 생성한 view를 전달하여 그 값을 리턴
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeAdapter.NoticeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notice_list, parent, false) //view 생성
        return NoticeViewHolder(view) //뷰홀더에 뷰 전달
    }

    //화면에 전달
    //비어있는 viewHolder들에 데이터값 집어넣기
    override fun onBindViewHolder(holder: NoticeAdapter.NoticeViewHolder, position: Int) {
        holder.major.text = noticeList.get(position).major
        holder.category.text = noticeList.get(position).category
        holder.title.text = noticeList.get(position).title
        holder.context.text = noticeList.get(position).context.toString()
        holder.createdAt.text = noticeList.get(position).createdAt.toString()//문자열로 변환
        noticeList.get(position).heart?.let { holder.heart.setImageResource(it) }
    }

    //전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return noticeList.size
    }

    //화면에 표시될 아이템 뷰 저장하는 객체
    //공지박스 하나가 한개의 Holder, 하나의 홀더 안에 들어갈 필드값들 명시
    inner class NoticeViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val major = itemView.findViewById<TextView>(R.id.tv_major) // 학과
        val category = itemView.findViewById<TextView>(R.id.tv_category) //카테고리
        val title = itemView.findViewById<TextView>(R.id.tv_title) // 제목
        val context = itemView.findViewById<TextView>(R.id.tv_context) // 내용요약
        val createdAt = itemView.findViewById<TextView>(R.id.tv_createdAt) // 날짜
        val heart = itemView.findViewById<ImageButton>(R.id.img_heart) // 관심목록
    }
}