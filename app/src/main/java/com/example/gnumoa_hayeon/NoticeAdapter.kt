package com.example.gnumoa_hayeon

import android.annotation.SuppressLint
import android.content.Context

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import com.google.gson.Gson
import kotlin.collections.ArrayList

object SharedDB {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("HeartPost", Context.MODE_PRIVATE)
    }

    fun getInstance(): SharedPreferences {
        if(!this::sharedPreferences.isInitialized) {
            throw java.lang.IllegalStateException("SharedPreferencesSingleton is not initialized")
        }
        return sharedPreferences
    }
}

// : -> 상속
// 1. Notice_list 데이터 클래스를 들고와서 ArrayList로 리스트화 시킨 것을 noticeList 변수에 넣음
// 2. 리사이클러뷰에 있는 어댑터 속성 가져오기
@SuppressLint("NotifyDataSetChanged")
class NoticeAdapter(context: Context) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    var noticeList: ArrayList<Notice_list> = arrayListOf()

    //요약 보여주기 - 공지 전체내용 중 일부(80자)만 가져옴
    //context가 null 또는 empty가 아니라면 fullText 값을 계산한 후 80자 이상인 경우 일부를 자르고 "..."을 붙입니다.
    private fun getContextPreview(context: List<String>): String {
        if (context.isNotEmpty()) {
            val fullText = context[0]
            return if (fullText.length > 70) "${fullText.substring(0, 70)}..." else fullText
        }
        return ""
    }

    private val db = FirebaseFirestore.getInstance()

    private val largeCollections = listOf(
        "간호대학",
        "건설환경공과대학",
        "경영대학",
        "공과대학",
        "농업생명과학대학",
        "법과대학",
        "본부대학1",
        "본부대학2",
        "사범대학",
        "사회과학대학",
        "수의과대학",
        "약학대학",
        "융합기술공과대학",
        "의과대학",
        "인문대학",
        "자연과학대학",
        "해양과학대학"
    )

    val subCollections = listOf(
        "공지사항-회계세무학부",
        "공지사항-회계학과(구경남과기대)",
        "공지사항-회계학과(구경상대)",
        "공지사항",
        "취업정보",
        "채용공고",
        "공지사항-가좌",
        "공지사항-칠암",
        "장학공지",
        "전체공지",
        "대외활동",
        "학부공지",
        "졸업공지",
        "기관공지사항",
        "비교과프로그램",
        "행사공지",
        "행사-기타공지",
        "취업-상담"
    )
//    val majorCollection = listOf(
//        "간호대학",
//        "건설시스템공학과",
//        "디자인비즈니스학과",
//        "인테리어재료공학과",
//        "조경학과",
//        "경영정보학과",
//        "경영학부",
//        "국제통상학부",
//        "산업경영학과",
//        "스마트유통물류학과",
//        "회계세무학부",
//        "건축공학부",
//        "건축학과",
//        "기계공학부",
//        "나노·신소재공학부 고분자공학전공",
//        "나노·신소재공학부 금속재료공학전공",
//        "도시공학과",
//        "반도체공학과 산업시스템공학부",
//        "전기공학과",
//        "제어로봇공학과",
//        "토목공학과",
//        "항공우주및소프트웨어공학부",
//        "화학공학과",
//        "농학과",
//        "스마트농산업학과",
//        "식물의학과",
//        "식품공학부",
//        "원예과학부",
//        "지역시스템공학과",
//        "축산과학부",
//        "환경산림과학부",
//        "환경생명화학과",
//        "법과대학",
//        "기계융합공학과",
//        "휴먼헬스케어학과",
//        "교육학과",
//        "국어교육과",
//        "물리교육과",
//        "생물교육과",
//        "수학교육과",
//        "영어교육과",
//        "유아교육과",
//        "일반사회교육과",
//        "일어교육과",
//        "지리교육과",
//        "화학교육과",
//        "경제학부",
//        "사회복지학부",
//        "사회학과",
//        "심리학과",
//        "아동가족학과",
//        "정치외교학과",
//        "행정학과",
//        "수의과대학",
//        "약학대학",
//        "기계소재융합공학부",
//        "메카트로닉스공학부",
//        "미래자동차공학과",
//        "에너지공학과",
//        "융합전자공학부",
//        "의과대학",
//        "국어국문학과",
//        "독어독문학과",
//        "러시아학과",
//        "불어불문학과",
//        "사학과",
//        "영어영문학부 영어영문학전공",
//        "영어영문학부 영어전공",
//        "중어중문학과",
//        "철학과",
//        "한문학과",
//        "물리학과",
//        "생명과학부",
//        "수학과",
//        "식품영양학과",
//        "의류학과",
//        "정보통계학과",
//        "제약공학과",
//        "지질과학과",
//        "컴퓨터과학부 컴퓨터과학전공",
//        "컴퓨터과학부 컴퓨터소프트웨어전공",
//        "항노화신소재과학과",
//        "화학과",
//        "기계시스템공학과",
//        "스마트에너지기계공학과",
//        "양식생명과학과",
//        "조선해양공학과",
//        "조선해양과학과",
//        "지능형통신공학과",
//        "해양수산경영학과",
//        "해양토목공학과",
//        "해양환경공학과"
//    )

    init {
        val ChangeMajorInfo = context.getSharedPreferences("MajorPost", Context.MODE_PRIVATE)
        val allKeys: Set<String> = ChangeMajorInfo.all.keys
        if (allKeys.isNotEmpty()) {
            for (largeCollection in largeCollections) {
                for (key in allKeys) {
                    for (category in subCollections) {
                        val ref =
                            db.collection(largeCollection).document(key).collection(category)
                        ref.addSnapshotListener { querySnapshot, _ ->
                            if (querySnapshot != null) {
                                for (snapshot in querySnapshot.documents) {
                                    val item = snapshot.toObject(Notice_list::class.java)
                                    noticeList.add(item!!)
                                }
                                noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
                                notifyDataSetChanged()
                            }
                        }

                    }
                }
            }
        }
//        else {
//            for (largeCollection in largeCollections) {
//                for (key in majorCollection) {
//                    for (category in subCollections) {
//                        val ref =
//                            db.collection(largeCollection).document(key).collection(category)
//                        ref.addSnapshotListener { querySnapshot, _ ->
//                            if (querySnapshot != null) {
//                                for (snapshot in querySnapshot.documents) {
//                                    val item = snapshot.toObject(Notice_list::class.java)
//                                    noticeList.add(item!!)
//                                }
//                                noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                                notifyDataSetChanged()
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }


    //onCreateViewHolder, onBindViewHolder, getItemCount() => view 홀더가 생성되는 곳
    //NoticeViewHolder => view 홀더 정의하는 곳

    //공지화면이랑 공지리스트 병합
    // 1. LayoutInflater를 통해 설계해둔 리스트를 view 변수안에 넣어서 생성
    // 2. NoticeViewHolder에 생성한 view를 전달하여 그 값을 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notice_list, parent, false) //view 생성
        return NoticeViewHolder(view).apply {//뷰홀더에 뷰 전달

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = noticeList[position]
                    val intent = Intent(parent.context, NoticeDetailActivity::class.java)
                    intent.putExtra("data", clickedItem)
                    parent.context.startActivity(intent)
                }
            }
        }
    }

    //화면에 전달
    //비어있는 viewHolder들에 데이터값 집어넣기
    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.major.text = noticeList[position].major
        holder.category.text = noticeList[position].category
        holder.title.text = noticeList[position].title
        holder.context.text = getContextPreview(noticeList[position].context!!)
        holder.getInit(noticeList[position].major!!, noticeList[position].title!!)
        holder.bind(noticeList[position])


        // Firestore Timestamp 객체를 Date 객체로 변환
        val timestamp = noticeList[position].createdAt as Timestamp
        val date = timestamp.toDate()

        // SimpleDateFormat을 사용하여 원하는 형식으로 포맷팅
        val sdf = SimpleDateFormat("yy / MM / dd", Locale.getDefault())
        holder.createdAt.text = sdf.format(date)

    }

    //전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return noticeList.size
    }

    fun serializeData(data: Any): String {
        val gson = Gson()
        return gson.toJson(data) //JSON 형식의 문자열로 변환
    }

    //화면에 표시될 아이템 뷰 저장하는 객체
    //공지박스 하나가 한개의 Holder, 하나의 홀더 안에 들어갈 필드 값들 명시
    inner class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val major: TextView = itemView.findViewById(R.id.tv_major) // 학과
        val category: TextView = itemView.findViewById(R.id.tv_category) //카테고리
        val title: TextView = itemView.findViewById(R.id.tv_title) // 제목
        val context: TextView = itemView.findViewById(R.id.tv_context) // 내용요약
        val createdAt: TextView = itemView.findViewById(R.id.tv_createdAt) // 날짜
        val heart: ImageButton = itemView.findViewById(R.id.img_heart) // 관심목록

        init {
            SharedDB.init(itemView.context)
        }

        private val changeHeartInfo: SharedPreferences = SharedDB.getInstance()

        //하트 상태 고정시키는 함수
        fun getInit(major: String, title: String) {
            //SharedPreferences는 앱의 데이터를 키-값 쌍으로 저장하기 위한 인터페이스
            val key = major + "_" + title
            if (changeHeartInfo.contains(key)) {
                heart.setImageResource(R.drawable.full_heart)
            } else {
                heart.setImageResource(R.drawable.empty_heart)
            }
        }

        fun bind(noticeItems: Notice_list) {
            heart.setOnClickListener {
                noticeItems.heart = !noticeItems.heart //하트 상태 변경

                val item = noticeList[adapterPosition]

                val key = item.major + "_" + item.title //key 값 이름
                val heartEditor = changeHeartInfo.edit()
                val serializedData = serializeData(item) // 데이터 직렬화->(키:값) 형태로 변환

                if (noticeItems.heart) {
                    heart.setImageResource(R.drawable.full_heart)

                    heartEditor.putString(key, serializedData) // 데이터 저장
                    heartEditor.apply()
                    Toast.makeText(itemView.context, "관심목록에 저장되었습니다.", Toast.LENGTH_SHORT).show()

                } else {
                    heart.setImageResource(R.drawable.empty_heart)
                    heartEditor.remove(key) // 데이터 삭제
                    heartEditor.apply()
                    Toast.makeText(itemView.context, "관심목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()

                    val allEntries: Map<String, *> = changeHeartInfo.all
                    val dataSize = allEntries.size //저장소에 저장된 아이템 개수
                    Log.d("dataSize", dataSize.toString())
                }
            }
        }
    }
}
