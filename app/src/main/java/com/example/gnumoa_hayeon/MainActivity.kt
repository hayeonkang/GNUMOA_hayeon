package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        var handler = Handler()
        handler.postDelayed( {
            var intent = Intent( this, HomeActivity::class.java)
            startActivity(intent)
        }, 3000)

        //setContentView(binding.root)

        /*binding.homeButton2.setOnClickListener{
            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)*/
        }
    }
