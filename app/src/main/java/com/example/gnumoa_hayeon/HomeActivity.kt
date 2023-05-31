package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging


class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lateinit var noticeAdapter: NoticeAdapter


        binding.majorButton.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish();
        }

//        binding.homeButton.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish();
//        }
        binding.heartButton.setOnClickListener {
            SharedDB.init(this)
            val changeHeartInfo = SharedDB.getInstance()
            val allEntries: Map<String, *> = changeHeartInfo.all

            if(allEntries.isEmpty()) {
                val intent = Intent(this, Heart0Activity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent =
                    Intent(this, HeartActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        noticeAdapter = NoticeAdapter(this) // NoticeAdapter 초기화


        //리사이클러뷰 레이아웃 화면에 바인딩
        binding.rvNoticeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNoticeList.setHasFixedSize(true)

        //리사이클러뷰랑 뷰 정의해놓은 어댑터 연결
        binding.rvNoticeList.adapter = noticeAdapter

    }

}



