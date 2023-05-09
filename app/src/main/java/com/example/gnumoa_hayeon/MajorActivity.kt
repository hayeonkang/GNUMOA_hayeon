package com.example.gnumoa_hayeon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityMajorBinding

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

        // 대학별 메이저 리스트 생성
//        val majorLists = mutableListOf(
//            Majorlist(
//                "인문대",0,
//
//                mutableListOf(
//                    Submajor("영문과", 0, 0),
//                    Submajor("수학과", 0, 0),
//                    Submajor("러시아학과", 0, 0)
//                )
//            ),
//            Majorlist(
//                "자연대",0,
//
//                mutableListOf(
//                    Submajor("생명과학부", 0, 0),
//                    Submajor("CS", 0, 0),
//                    Submajor("정통", 0, 0)
//                )
//
//            ),
//            Majorlist(
//                "사회대",0,
//
//                mutableListOf(
//                    Submajor("심리학과", 0, 0),
//                    Submajor("사회학과", 0, 0),
//                    Submajor("정치외교학과", 0, 0)
//                )
//            )
//        )
        val majorLists = mutableListOf(
            Majorlist(
                "인문대",0,
                mutableListOf(
                    Submajor("영문과", 0, 0),
                    Submajor("수학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0),
                    Submajor("러시아학과", 0, 0)
                )
            ),
            Majorlist(
                "자연대",0,
                mutableListOf(
                    Submajor("생명과학부", 0, 0),
                    Submajor("CS", 0, 0),
                    Submajor("정통", 0, 0)
                )
            ),
            Majorlist(
                "경영대",0,
                mutableListOf(
                    Submajor("심리학과", 0, 0),
                    Submajor("사회학과", 0, 0),
                    Submajor("정치외교학과", 0, 0)
                )
            ),
            Majorlist(
                "공과대",0,
                mutableListOf(
                    Submajor("심리학과", 0, 0),
                    Submajor("사회학과", 0, 0),
                    Submajor("정치외교학과", 0, 0)
                )
            ),
            Majorlist(
                "농대",0,
                mutableListOf(
                    Submajor("심리학과", 0, 0),
                    Submajor("사회학과", 0, 0),
                    Submajor("정치외교학과", 0, 0)
                )
            ),
            Majorlist(
                "의대",0,
                mutableListOf(
                    Submajor("심리학과", 0, 0),
                    Submajor("사회학과", 0, 0),
                    Submajor("정치외교학과", 0, 0)
                )
            )
        )



        //리사이클러뷰 레이아웃 화면에 바인딩
        binding.rvMajorList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMajorList.setHasFixedSize(true)


        //리사이클러뷰랑 뷰 정의해놓은 어댑터 연결
        //binding.rvMajorList.adapter = SubMajorAdapter()

        // Create an instance of the SubMajorAdapter
        val subMajorAdapter = SubMajorAdapter()

        // Set the adapter for the RecyclerView with the MajorAdapter instance
        binding.rvMajorList.adapter = MajorAdapter(majorLists, subMajorAdapter)

    }
}

