package com.example.gnumoa_hayeon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityMajorBinding


class MajorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMajorBinding
    private lateinit var adapter: Major_RecyclerViewAdapter
    private val name = ArrayList<String>()
    private val items = ArrayList<Recycler_item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMajorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMajorList.layoutManager = LinearLayoutManager(this)
        adapter = Major_RecyclerViewAdapter(name, items)
        binding.rvMajorList.adapter = adapter

        // 리사이클러뷰 헤더
        name.add("인문대학")
        name.add("사회과학대학")
        name.add("경영대학")
        name.add("공과대학")
        name.add("농업생명과학대학")
        name.add("법과대학")
        name.add("사범대학")
        name.add("수의과대학")
        name.add("의과대학")
        name.add("간호대학")
        name.add("해양과학대학")
//        name.add("약학대학")
//        name.add("본부대학 I")
//        name.add("생명과학대학")
//        name.add("건설환경공과대학")
//        name.add("융합기술공과대학")
//        name.add("인문사회과학대학")
//        name.add("상경대학")
//        name.add("본부대학 II")

        // 리사이클러뷰 안 리사이클러뷰에 들어갈 데이터
        items.add(Recycler_item("", "1",null))
        items.add(Recycler_item("", "2",null))
        items.add(Recycler_item("", "3",null))
        items.add(Recycler_item("", "4",null))
        items.add(Recycler_item("", "5",null))
        items.add(Recycler_item("", "6",null))
        items.add(Recycler_item("", "7",null))
        items.add(Recycler_item("", "8",null))
        items.add(Recycler_item("", "9",null))
        items.add(Recycler_item("", "10",null))


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
//        val innerRecyclerView: RecyclerView = findViewById(R.id.rv_majorList)
//        innerRecyclerView.isNestedScrollingEnabled = false

    }
        // 리사이클러뷰 안 리사이클러뷰 아이템 구조
        class Recycler_item(image: String, title: String, heart: Button?) {
            var image: String
            var title: String
            var heart: Button? = null

            init {
                this.image = image
                this.title = title
                this.heart = heart

                this.heart?.setOnClickListener {
                    heart?.setBackgroundResource(R.drawable.baseline_favorite_24)

                }
            }
        }
    }
