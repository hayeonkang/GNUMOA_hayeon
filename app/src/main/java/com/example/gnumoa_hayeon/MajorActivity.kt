package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gnumoa_hayeon.databinding.ActivityMajorBinding
import org.checkerframework.checker.units.qual.m

class MajorActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMajorBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish();
        }

        binding.heartButton.setOnClickListener {
            val intent = Intent(this, HeartActivity::class.java)
            startActivity(intent)
            finish();
        }

        //리사이클러뷰 레이아웃 화면에 바인딩
        binding.rvMajorList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMajorList.setHasFixedSize(true)

        //리사이클러뷰랑 뷰 정의해놓은 어댑터 연결
        binding.rvMajorList.adapter = MajorAdapter()
    }


}