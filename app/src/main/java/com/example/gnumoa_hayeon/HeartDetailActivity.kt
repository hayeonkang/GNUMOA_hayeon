package com.example.gnumoa_hayeon

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gnumoa_hayeon.databinding.HeartDetailBinding
import com.google.firebase.Timestamp
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class HeartDetailActivity: AppCompatActivity() {
        private val binding by lazy {
        HeartDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val heartData = intent.getParcelableExtra<Notice_list>("heart_data")

        // Firestore Timestamp 객체를 Date 객체로 변환
        val timestamp = heartData!!.createdAt as Timestamp
        val date = timestamp.toDate()

        // SimpleDateFormat을 사용하여 원하는 형식으로 포맷팅
        val sdf = SimpleDateFormat("yy / MM / dd", Locale.getDefault())


        binding.heartTitle.text = heartData.title
        binding.heartCategory.text = heartData.category
        binding.heartMajor.text = heartData.major
        binding.heartDate.text = sdf.format(date)

        //hashmap 형태 파일 나타내기+텍스트뷰 하이퍼링크 처리
        val files = heartData.fileUrls as HashMap<String, String>
        //val files = heartData?.fileUrls ?: hashMapOf()
        val stringBuilder = SpannableStringBuilder()
        var index = 1

        for ((key, value) in files) {
            val prefix = "$index"
            stringBuilder.append("$prefix. $key\n")
            // 파일 이름과 prefix를 StringBuilder에 추가
            var isClicked = false
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    isClicked = true
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
                    startActivity(intent)
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    if (isClicked) {
                        ds.color = Color.rgb(139,0,255) // 클릭했을 때 색상 변경
                    } else {
                        ds.color = Color.BLUE // 클릭 전에는 파란색으로 유지
                    }
                    ds.isUnderlineText = true // 밑줄 추가
                }
            }
            //클릭 가능한 시작 부분과 끝부분 정의
            val start = stringBuilder.indexOf("$key\n")
            val end = start + key.length
            stringBuilder.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            // 파일 이름에 클릭 이벤트를 추가
            index++
        }

        binding.heartFileUrls.apply {
            text = stringBuilder
            movementMethod = LinkMovementMethod.getInstance()
        }


        //해당 url로 이동
        val url = heartData.baseUrl

        binding.heartBtnUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        //html 웹뷰로 나타내기
        val html = heartData.html

        if (html != null) {
            binding.noticeHtml.loadData(html, "text/html", "UTF-8")
            binding.noticeHtml.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
        }

        binding.heartBtnBack.setOnClickListener {
            finish()
        }

        SharedDB.init(this)

        val changeHeartInfo: SharedPreferences = SharedDB.getInstance()

        //SharedPreferences는 앱의 데이터를 키-값 쌍으로 저장하기 위한 인터페이스
        val key = heartData.major + "_" + heartData.title
        if (changeHeartInfo.contains(key)) {
            binding.heartDetailHeart
            binding.heartDetailHeart.setImageResource(R.drawable.full_heart)
        }else{
            binding.heartDetailHeart.setImageResource(R.drawable.empty_heart)
        }

        fun serializeData(data: Any): String {
            val gson = Gson()
            return gson.toJson(data) //JSON 형식의 문자열로 변환
        }


        binding.heartDetailHeart.setOnClickListener {
            heartData.heart = !heartData.heart //하트 상태 변경

            val key = heartData.major + "_" + heartData.title //key 값 이름
            val heartEditor = changeHeartInfo.edit()
            val serializedData = serializeData(heartData) // 데이터 직렬화->(키:값) 형태로 변환

            if(changeHeartInfo.contains(key)){
                binding.heartDetailHeart.setImageResource(R.drawable.empty_heart)
                heartEditor.remove(key) // 데이터 삭제
                heartEditor.apply()
                Toast.makeText(this, "관심목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()

                val allEntries: Map<String, *> = changeHeartInfo.all
                val dataSize = allEntries.size //저장소에 저장된 아이템 개수
                Log.d("dataSize", dataSize.toString())
            }else{
                binding.heartDetailHeart.setImageResource(R.drawable.full_heart)
                Log.d("noticeItems", heartData.heart.toString())

                heartEditor.putString(key, serializedData) // 데이터 저장
                heartEditor.apply()
                Toast.makeText(this, "관심목록에 저장되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}