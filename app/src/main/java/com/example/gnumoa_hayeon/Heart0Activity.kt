package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat.startActivity
import com.example.gnumoa_hayeon.databinding.ActivityHeart0Binding

class Heart0Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHeart0Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
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
}
}