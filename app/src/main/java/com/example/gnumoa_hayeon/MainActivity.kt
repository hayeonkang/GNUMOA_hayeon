package com.example.gnumoa_hayeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gnumoa_hayeon.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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

        val database = Firebase.firestore
        val testData = hashMapOf(
            "name" to "khy",
            "age" to "22",
            "country" to "Korea"
        )
        database.collection("test").add(testData)
    }
}
