package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityHomeBinding



class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.majorButton.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish();
        }
        binding.heartButton.setOnClickListener {
            val intent = Intent(this, HeartActivity::class.java)
            startActivity(intent)
            finish();
        }

        //리사이클러뷰 레이아웃 화면에 바인딩
        binding.rvNoticeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNoticeList.setHasFixedSize(true)

        //리사이클러뷰랑 뷰 정의해놓은 어댑터 연결
        binding.rvNoticeList.adapter = NoticeAdapter()

        }

    }




