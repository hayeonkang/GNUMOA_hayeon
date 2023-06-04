package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gnumoa_hayeon.databinding.ActivityHeart0Binding

class Major0Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHeart0Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.majorButton.setOnClickListener {
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
            finish()
        }
//        binding.heartButton.setOnClickListener {
//            val intent = Intent(this, HeartActivity::class.java)
//            startActivity(intent)
//            finish();
//        }
}
}