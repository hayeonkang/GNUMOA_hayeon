package com.example.gnumoa_hayeon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityMajorBinding


class MajorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMajorBinding
    private lateinit var adapter: Major_RecyclerViewAdapter
    private val name = ArrayList<Recycler_item_out>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMajorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMajorList.layoutManager = LinearLayoutManager(this)
        adapter = Major_RecyclerViewAdapter(name)
        binding.rvMajorList.adapter = adapter

        // 리사이클러뷰 헤더
        name.add(Recycler_item_out("인문대학", mutableListOf(
            Recycler_item("", "영어영문학부 영어영문학전공", null),
            Recycler_item("", "영어영문학부 영어전공", null),
            Recycler_item("", "국어국문학과", null),
            Recycler_item("", "독어독문학과", null),
            Recycler_item("", "러시아학과", null),
//            Recycler_item("", "민속무용학과", null),
            Recycler_item("", "불어불문학과", null),
            Recycler_item("", "사학과", null),
            Recycler_item("", "중어중문학과", null),
            Recycler_item("", "철학과", null),
            Recycler_item("", "한문학과", null)
        )))
        name.add(Recycler_item_out("사회과학대학", mutableListOf(
            Recycler_item("", "경제학부", null),
            Recycler_item("", "사회복지학부", null),
            Recycler_item("", "사회학과", null),
            Recycler_item("", "심리학과", null),
            Recycler_item("", "아동가족학과", null),
            Recycler_item("", "정치외교학과", null),
            Recycler_item("", "행정학과", null)
        )))
        name.add(Recycler_item_out("자연과학대학", mutableListOf(
            Recycler_item("", "생명과학부", null),
            Recycler_item("", "물리학과", null),
            Recycler_item("", "수학과", null),
            Recycler_item("", "식품영양학과", null),
            Recycler_item("", "의류학과", null),
            Recycler_item("", "정보통계학과", null),
            Recycler_item("", "제약공학과", null),
            Recycler_item("", "지질과학과", null),
            Recycler_item("", "항노화신소재과학과", null),
            Recycler_item("", "화학과", null),
            Recycler_item("", "컴퓨터과학부 컴퓨터과학전공", null),
            Recycler_item("", "컴퓨터과학부 컴퓨터소프트웨어전공", null)
        )))
        name.add(Recycler_item_out("경영대학", mutableListOf(
            Recycler_item("", "경영학부", null),
            Recycler_item("", "국제통상학부", null),
            Recycler_item("", "회계세무학부", null),
            Recycler_item("", "경영정보학과", null),
            Recycler_item("", "산업경영학과", null),
            Recycler_item("", "스마트유통물류학과", null)
        )))
        name.add(Recycler_item_out("공과대학", mutableListOf(
            Recycler_item("", "건축공학부", null),
//            Recycler_item("", "건축공학부 건축공학전공", null),
//            Recycler_item("", "건축공학부 건축시스템공학전공", null),
            Recycler_item("", "기계공학부", null),
            Recycler_item("", "나노·신소재공학부 고분자공학전공", null),
            Recycler_item("", "나노·신소재공학부 금속재료공학전공", null),
//            Recycler_item("", "나노·신소재공학부 세라믹공학전공", null),
            Recycler_item("", "산업시스템공학부", null),
            Recycler_item("", "항공우주및소프트웨어공학부", null),
            Recycler_item("", "건축학과", null),
            Recycler_item("", "도시공학과", null),
            Recycler_item("", "반도체공학과", null),
            Recycler_item("", "전기공학과", null),
//            Recycler_item("", "전자공학과", null),
            Recycler_item("", "제어로봇공학과", null),
            Recycler_item("", "토목공학과", null),
            Recycler_item("", "화학공학과", null)
        )))
        name.add(Recycler_item_out("농업생명과학대학", mutableListOf(
//            Recycler_item("", "식품자원경제학과", null),
            Recycler_item("", "동물생명융합학부", null),
            Recycler_item("", "식품공학과", null),
            Recycler_item("", "원예과학부", null),
            Recycler_item("", "축산과학부", null),
            Recycler_item("", "환경산림과학부", null),
            Recycler_item("", "농학과", null),
            Recycler_item("", "스마트농산업학과", null),
            Recycler_item("", "식물의학과", null),
            Recycler_item("", "환경생명화학과", null),
            Recycler_item("", "환경재료과학과", null),
            Recycler_item("", "생물산업기계공학과", null),
            Recycler_item("", "지역시스템공학과", null)
        )))
        name.add(Recycler_item_out("법과대학", mutableListOf(
            Recycler_item("", "법학과", null)
        )))
        name.add(Recycler_item_out("사범대학", mutableListOf(
            Recycler_item("", "교육학과", null),
            Recycler_item("", "국어교육과", null),
            Recycler_item("", "역사교육과", null),
            Recycler_item("", "영어교육과", null),
            Recycler_item("", "유아교육과", null),
            Recycler_item("", "윤리교육과", null),
            Recycler_item("", "일반사회 교육과", null),
            Recycler_item("", "일어교육과", null),
            Recycler_item("", "지리교육과", null),
            Recycler_item("", "물리교육과", null),
            Recycler_item("", "생물교육과", null),
            Recycler_item("", "수학교육과", null),
            Recycler_item("", "화학교육과", null),
            Recycler_item("", "미술교육과", null),
            Recycler_item("", "음악교육과", null),
            Recycler_item("", "체육교육과", null)
        )))
        name.add(Recycler_item_out("수의과대학", mutableListOf(
            Recycler_item("", "수의과대학", null)
//            Recycler_item("", "수의학과", null),
//            Recycler_item("", "수의예과", null)
        )))
        name.add(Recycler_item_out("의과대학", mutableListOf(
            Recycler_item("", "의학과", null),
            Recycler_item("", "의예과", null)
        )))
        name.add(Recycler_item_out("간호대학", mutableListOf(
            Recycler_item("", "간호대학", null)
        )))
        name.add(Recycler_item_out("해양과학대학", mutableListOf(
            Recycler_item("", "해양수산경영대학", null),
            Recycler_item("", "미래산업융합학과", null),
            Recycler_item("", "양식생명과학과", null),
//            Recycler_item("", "해양경찰시스템학과", null),
            Recycler_item("", "기계시스템공학과", null),
            Recycler_item("", "스마트에너지기계공학과", null),
            Recycler_item("", "조선해양과학과", null),
            Recycler_item("", "지능형통신공학과", null),
            Recycler_item("", "해양식품공학과", null),
            Recycler_item("", "해양토목공학과", null),
            Recycler_item("", "해양환경공학과", null)
        )))
        name.add(Recycler_item_out("약학대학", mutableListOf(
            Recycler_item("", "약학대학", null)
        )))
        name.add(Recycler_item_out("본부대학 I", mutableListOf(
            Recycler_item("", "기계융합공학과", null)
        )))
        name.add(Recycler_item_out("건설환경공과대학", mutableListOf(
            Recycler_item("", "건설시스템공학과", null),
            Recycler_item("", "인테리어재료공학과", null),
            Recycler_item("", "조경학과", null),
            Recycler_item("", "환경공학과", null),
            Recycler_item("", "디자인비지니스학과", null)
        )))
        name.add(Recycler_item_out("융합기술공과대학", mutableListOf(
            Recycler_item("", "기계소재융합공학부", null),
            Recycler_item("", "메카트로닉스공학부", null),
            Recycler_item("", "융합전자공학부", null),
            Recycler_item("", "에너지공학과", null),
            Recycler_item("", "미래자동차공학과", null)
        )))
        name.add(Recycler_item_out("본부대학 II", mutableListOf(
            Recycler_item("", "휴먼헬스케어학과", null)
        )))
        // Add more headers and items as needed

        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.heartButton.setOnClickListener {
            val intent = Intent(this, HeartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    class Recycler_item_out(val name: String, val items: MutableList<Recycler_item>)

    // 리사이클러뷰 안 리사이클러뷰 아이템 구조
    class Recycler_item(val image: String, val title: String, val heart: Button?) {
//        init {
//            this.heart?.setOnClickListener {
//                heart?.setBackgroundResource(R.drawable.baseline_favorite_24)
//            }
//        }
    }
}
