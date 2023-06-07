package com.example.gnumoa_hayeon

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gnumoa_hayeon.databinding.ActivityHeart0Binding

class Heart0Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHeart0Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            val changeMajorInfo = getSharedPreferences("MajorPost", Context.MODE_PRIVATE)

            val allEntries: Map<String, *> = changeMajorInfo.all

            if (allEntries.isEmpty()) {
                val intent = Intent(this, Home0Activity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent =
                    Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.majorButton.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.homeTv.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.majorTv.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}