package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnumoa_hayeon.databinding.ActivityHeartBinding


class HeartActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHeartBinding.inflate(layoutInflater)
    }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(binding.root)
            binding.homeButton.setOnClickListener {
                val intent =
                    Intent(this, HomeActivity::class.java)
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