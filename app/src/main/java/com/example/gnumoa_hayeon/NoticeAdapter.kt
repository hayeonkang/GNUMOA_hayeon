package com.example.gnumoa_hayeon

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


// : -> 상속
// 1. Notice_list 데이터 클래스를 들고와서 ArrayList로 리스트화 시킨 것을 noticeList 변수에 넣음
// 2. 리사이클러뷰에 있는 어댑터 속성 가져오기
class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Notice_list)
    }

    var noticeList: ArrayList<Notice_list> = arrayListOf()

    //요약 보여주기 - 공지 전체내용 중 일부(80자)만 가져옴
    //context가 null 또는 empty가 아니라면 fullText 값을 계산한 후 80자 이상인 경우 일부를 자르고 "..."을 붙입니다.
    private fun getContextPreview(context: List<String>): String {
        if (!context.isNullOrEmpty()) {
            val fullText = context[0]
            return if (fullText.length > 70) "${fullText.substring(0, 70)}..." else fullText
        }
        return ""
    }


    // documents와 collections1, collections2 리스트를 만들어서 중첩 반복문으로 각각의 문서와 컬렉션을 가져와서 SnapshotListener를 등록
    // 리스트에 있는 모든 컬렉션들의 문서 데이터가 noticeList에 추가
    // notifyDataSetChanged() 함수를 호출하여 RecyclerView를 업데이트
//    val allDocuments = listOf(
//        "dokmun", "korea", "china", "english", "france", "hanmun", "his", "korea",
//        "minsok", "russia", "sophia", "biomat", "chem", "cloth", "cs", "cse",
//        "foodnutri", "geology", "ls", "math", "pharmgine", "psysics", "stat,",
//        "economics", "pa", "polisci", "psychology", "socialwelfare", "socio",
//        "business"
//    )

//    init {
//        val firestore = FirebaseFirestore.getInstance()
//
//        firestore?.collection("inmun")?.document("china")?.collection("공지사항")?.addSnapshotListener {
//                querySnapshot, firebaseFirestoreException -> noticeList.clear()
//
//            for (snapshot in querySnapshot!!.documents) {
//                var item = snapshot.toObject(Notice_list::class.java)
//                noticeList.add(item!!)
//            }
//            noticeList.sortByDescending { it.createdAt }
//            notifyDataSetChanged()
//        }
//    }


    init {
        val db = FirebaseFirestore.getInstance()

        val mainDocuments = listOf("accounting","business","industry","mis","smart","trade",
        "ab","agrieng","agronomy","alc","as","foodsci","fr","hortic","smartagro",
//        "cap",
//        "archeng", "me", "polymer", "metals", "ise", "anse", "arch", "urban", "se", "el", "control", "civil", "chemeng",
//            "civilinfra", "im", "landscape", "env", "design",
//        "ls", "physics", "math", "foodnutri", "cloth", "stat", "pharmgine", "geology", "biomat", "chem", "cs", "cse",
//        "economics", "social", "socio", "psychology", "polisci", "pa",
//        "mm", "mecha", "cele", "energyeng", "car",
//        "healthcare",
//        "engLiter", "engLang", "korea", "dokmun", "russia", "minsok", "france", "history", "china", "sophia", "hanmun",
//        "law",
//        "fba", "mirae", "sea", "maripoli", "gse", "smartam", "naoe", "ace", "seafood", "oce", "marenv",
//        "mce",
//        "medicine",
//        "pharm",
//        "pedagogy", "korlan", "history", "englishedu", "ecedu", "ethics", "sed", "edjapan", "geoedu", "physed", "bioedu", "mathedu", "chemedu", "artedu", "musicedu", "physicaledu",
//        "vet"
        )

        val subCollections = listOf("공지사항-회계세무학부","공지사항-회계학과(구경남과기대)","공지사항-회계학과(구경상대)","공지사항","취업정보","채용공고",
            "공지사항-가좌","공지사항-칠암","장학공지","전체공지","대외활동","학부공지","졸업공지","기관공지사항","비교과프로그램","행사공지","행사-기타공지",
        "취업-상담"
        )

        for(major in mainDocuments) {
            for(category in subCollections) {
                val bizRef = db.collection("biz").document(major).collection(category)
                val calsRef = db.collection("cals").document(major).collection(category)
                val capRef = db.collection("cap").document(major).collection(category)
                val ceRef = db.collection("ce").document(major).collection(category)
                val ceeRef = db.collection("cee").document(major).collection(category)
                val cnsRef = db.collection("cns").document(major).collection(category)
                val cssRef = db.collection("css").document(major).collection(category)
                val cteRef = db.collection("cte").document(major).collection(category)
                val healthcareRef = db.collection("healthcare").document(major).collection(category)
                val inmunRef = db.collection("inmun").document(major).collection(category)
                val lawRef = db.collection("law").document(major).collection(category)
                val marsciRef = db.collection("marsci").document(major).collection(category)
                val mceRef = db.collection("mce").document(major).collection(category)
                val medicineRef = db.collection("medicine").document(major).collection(category)
                val pharmRef = db.collection("pharm").document(major).collection(category)
                val sadaeRef = db.collection("sadae").document(major).collection(category)
                val vetRef = db.collection("vet").document(major).collection(category)

                bizRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (snapshot in querySnapshot!!.documents) {
                        val item = snapshot.toObject(Notice_list::class.java)
                        noticeList.add(item!!)
                    }
                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
                    notifyDataSetChanged()
                }

                calsRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    for (snapshot in querySnapshot!!.documents) {
                        val item = snapshot.toObject(Notice_list::class.java)
                        noticeList.add(item!!)
                    }
                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
                    notifyDataSetChanged()
                }

//                capRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }

//                ceRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }

//                ceeRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }

//                cnsRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }

//                cssRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                cteRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                healthcareRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                inmunRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }

//                lawRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                marsciRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                medicineRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                mceRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                sadaeRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                pharmRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
//
//                vetRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    for (snapshot in querySnapshot!!.documents) {
//                        val item = snapshot.toObject(Notice_list::class.java)
//                        noticeList.add(item!!)
//                    }
//                    noticeList.sortByDescending { it.createdAt } // createdAt 필드를 기준으로 내림차순 정렬
//                    notifyDataSetChanged()
//                }
            }
        }

    }

    //onCreateViewHolder, onBindViewHolder, getItemCount() => view 홀더가 생성되는 곳
    //NoticeViewHolder => view 홀더 정의하는 곳

    //공지화면이랑 공지리스트 병합
    // 1. LayoutInflater를 통해 설계해둔 리스트를 view 변수안에 넣어서 생성
    // 2. NoticeViewHolder에 생성한 view를 전달하여 그 값을 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_list, parent, false) //view 생성
        return NoticeViewHolder(view).apply {//뷰홀더에 뷰 전달

            itemView.setOnClickListener {
                val position= adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = noticeList[position]
                    val intent = Intent(parent.context, DetailActivity::class.java)
                    intent.putExtra("data", clickedItem)
                    parent.context.startActivity(intent)
                }
            }
        }


    }

    //화면에 전달
    //비어있는 viewHolder들에 데이터값 집어넣기
    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.major.text = noticeList.get(position).major
        holder.category.text = noticeList.get(position).category
        holder.title.text = noticeList.get(position).title
        holder.context.text = getContextPreview(noticeList[position].context!!)
        //holder.createdAt.text = noticeList.get(position).createdAt.toString()//문자열로 변환
        noticeList.get(position).heart?.let { holder.heart.setImageResource(it) }


        // Firestore Timestamp 객체를 Date 객체로 변환
        val timestamp = noticeList.get(position).createdAt as Timestamp
        val date = timestamp.toDate()

        // SimpleDateFormat을 사용하여 원하는 형식으로 포맷팅
        val sdf = SimpleDateFormat("yy / MM / dd", Locale.getDefault())
        holder.createdAt.text = sdf.format(date)

    }

    //전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return noticeList.size
    }

    //화면에 표시될 아이템 뷰 저장하는 객체
    //공지박스 하나가 한개의 Holder, 하나의 홀더 안에 들어갈 필드 값들 명시
    inner class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val major = itemView.findViewById<TextView>(R.id.tv_major) // 학과
        val category = itemView.findViewById<TextView>(R.id.tv_category) //카테고리
        val title = itemView.findViewById<TextView>(R.id.tv_title) // 제목
        val context = itemView.findViewById<TextView>(R.id.tv_context) // 내용요약
        val createdAt = itemView.findViewById<TextView>(R.id.tv_createdAt) // 날짜
        val heart = itemView.findViewById<ImageButton>(R.id.img_heart) // 관심목록
    }
}