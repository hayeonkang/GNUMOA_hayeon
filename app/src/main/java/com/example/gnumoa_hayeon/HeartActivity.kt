package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gnumoa_hayeon.databinding.ActivityHeartBinding


class HeartActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHeartBinding.inflate(layoutInflater)}

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(binding.root)
            binding.homeButton.setOnClickListener {
                val intent =
                    android.content.Intent(this, com.example.gnumoa_hayeon.HomeActivity::class.java)
                startActivity(intent)
                finish();
            }
            binding.majorButton.setOnClickListener {
                val intent = Intent(this, MajorActivity::class.java)
                startActivity(intent)
                finish();
            }



    }
}