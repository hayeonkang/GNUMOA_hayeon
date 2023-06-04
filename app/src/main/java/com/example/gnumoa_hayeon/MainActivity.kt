package com.example.gnumoa_hayeon

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gnumoa_hayeon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val handler = Handler()
        handler.postDelayed({
//            var intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
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
        }, 3000)


//        val changeMajorInfo = getSharedPreferences("MajorPost", Context.MODE_PRIVATE)
//
//        val allEntries: Map<String, *> = changeMajorInfo.all
//
//        if (allEntries.isEmpty()) {
//            val intent = Intent(this, Home0Activity::class.java)
//            startActivity(intent)
//            finish()
//        } else {
//            val intent =
//                Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        setContentView(binding.root)

//        binding.imageButton.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }
    }
}


