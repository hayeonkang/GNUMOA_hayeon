package com.example.gnumoa_hayeon

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gnumoa_hayeon.databinding.NoticeDetailBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
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
//            binding.noticeContext.text = data!!.context.toString()
            binding.fileUrls.text = data!!.fileUrls.toString()

            val url = data!!.baseUrl

            binding.btnUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            val html = data!!.html

            if (html != null) {
                binding.noticeHtml.loadData(html, "text/html", "UTF-8")
                binding.noticeHtml.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
            }


        }

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }





    }
}
