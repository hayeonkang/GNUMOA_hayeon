package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityHeartBinding

class HeartActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHeartBinding.inflate(layoutInflater)
    }

    private val heartAdapter = HeartAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.homeButton.setOnClickListener {
            val intent =
                Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.majorButton.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish()
        }

        //리사이클러뷰 초기화
        binding.rvNoticeListHeart.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNoticeListHeart.adapter = heartAdapter
        //아이템이 추가되거나 삭제되면 호출하여 변경 반영
        heartAdapter.notifyDataSetChanged()

//        val heartDB = NoticeAdapter.SharedDB.getInstance()
//        val heartItems: Map<String, *> = heartDB.all



//        for((key,value) in heartItems) {
//            val serializedData = value as String
//            val item: Notice_list = deserializeData(serializedData)
//            heartAdapter.addItem(item)
//        }

    }

    // 데이터 직렬화
//    private fun serializeData(data: Any): String {
//        val gson = Gson()
//        return gson.toJson(data)
//    }

    // 데이터 역직렬화
//    private fun deserializeData(serializedData: String): Notice_list {
//        val gson = Gson()
//        return gson.fromJson(serializedData, Notice_list::class.java)
//    }
}