package com.example.gnumoa_hayeon

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.gnumoa_hayeon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 3000)

        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

//        val db = Firebase.firestore
//
//        val testData = hashMapOf(
//            "name" to "김희영",
//            "age" to "22",
//            "country" to "Korea"
//        )
//        db.collection("inmun").document("test").set(testData)

    }
}


