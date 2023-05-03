package com.example.gnumoa_hayeon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gnumoa_hayeon.databinding.NoticeDetailBinding
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity :AppCompatActivity() {
    private val binding by lazy {
        NoticeDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Notice_list>("data")

        // Firestore Timestamp 객체를 Date 객체로 변환
        val timestamp = data!!.createdAt as Timestamp
        val date = timestamp.toDate()

        // SimpleDateFormat을 사용하여 원하는 형식으로 포맷팅
        val sdf = SimpleDateFormat("yy / MM / dd", Locale.getDefault())

        if (data != null) {
            binding.noticeTitle.text = data!!.title
            binding.noticeCategory.text = data!!.category
            binding.noticeMajor.text = data!!.major
            binding.noticeDate.text = sdf.format(date)
            binding.noticeContext.text = data!!.context.toString()
        }

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
