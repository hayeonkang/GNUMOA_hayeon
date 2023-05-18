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

    var heartItems: ArrayList<Notice_list> = arrayListOf()
    private val noticeAdapter = NoticeAdapter()

    private val heartAdapter = HeartAdapter(heartItems)

//    fun addNoticeItem(heartItem: Notice_list) {
//        heartItems.add(heartItem)
//        heartAdapter.notifyItemInserted(heartItems.size - 1)
//    }
//
//    fun removeNoticeItem(noticeItem: Notice_list) {
//        val index = heartItems.indexOfFirst { it.id == noticeItem.id }
//        if (index != -1) {
//            heartItems.removeAt(index)
//            heartAdapter.notifyItemRemoved(index)
//        }
//    }

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


        //NoticeAdapter의 멤버 변수 사용하기 위해 객체 생성

//        noticeAdapter.noticeList = noticeItems
//        noticeAdapter.onHeartClickListener() = { heartItem ->
//            //클릭했을 때 공지 아이템 추가
//            if (heartItem.heart == true) {
//                heartItems.add(heartItem)
//                //추가된 아이템 새로고침
//                noticeAdapter.notifyItemInserted(heartItems.size - 1)
//            } else {
//                val index = heartItems.indexOfFirst { it.id == heartItem.id }
//                if (index != -1) {
//                    heartItems.removeAt(index)
//                    noticeAdapter.notifyItemRemoved(index) //공지 아이템 제거
//                }
//            }

//        noticeAdapter.onHeartClickListener = { heartItem ->
//            if (heartItem.heart == true) {
//                heartItems.add(heartItem)
//                //추가된 아이템 새로고침
//                heartAdapter.notifyItemInserted(heartItems.size - 1)
//            } else {
//                val index = heartItems.indexOfFirst { it.id == heartItem.id }
//                if (index != -1) {
//                    heartItems.removeAt(index)
//                    heartAdapter.notifyItemRemoved(index) //공지 아이템 제거
//                }
//            }
//        }

            //리사이클러뷰 초기화
            binding.rvNoticeListHeart.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//            binding.rvNoticeListHeart.setHasFixedSize(true)
            binding.rvNoticeListHeart.adapter = heartAdapter
            //아이템이 추가되거나 삭제되면 호출하여 변경 반영
            heartAdapter.notifyDataSetChanged()

            //noticeAdapter의 noticeList에 데이터 추가
//            for (noticeItem in noticeItems) {
//                if (noticeItem.heart)
//                    noticeAdapter.noticeList.add(noticeItem)
//            }
        }
    }

