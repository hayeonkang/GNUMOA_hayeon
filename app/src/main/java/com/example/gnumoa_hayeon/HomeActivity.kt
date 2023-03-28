package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_home)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish();
        }
        binding.majorButton.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish();
        }
        binding.heartButton.setOnClickListener {
            val intent = Intent(this, Heart0Activity::class.java)
            startActivity(intent)
            finish();
        }

        val noticeList = arrayListOf(
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart),
            Notice_list("컴퓨터과학과", "장학", "2023년도 장학생 선발", "장학금 신청서.hwd 파일 참고","23/03/28",R.drawable.empty_heart)
        )

        rv_noticeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_noticeList.setHasFixedSize(true)

        rv_noticeList.adapter = NoticeAdapter(noticeList)
    }
}