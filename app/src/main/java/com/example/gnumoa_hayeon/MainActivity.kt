package com.example.gnumoa_hayeon

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.gnumoa_hayeon.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging


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

        // Firebase Cloud Messaging 토큰 얻기
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result

                    // Firebase Firestore에 접근합니다.
                    val db = Firebase.firestore

                    // "Tokens" 컬렉션에 토큰 값을 가진 문서를 생성합니다.
                    val tokenDocument = db.collection("Tokens").document(token).set(mapOf<String, Any>())
                        .addOnSuccessListener {
                            Log.d("FCM Token", "토큰을 Firestore에 저장했습니다.")
                        }
                        .addOnFailureListener { exception ->
                            Log.w("FCM Token", "Firestore에 토큰을 저장하는 데 실패했습니다.", exception)
                        }
                } else {
                    Log.w("FCM Token", "토큰을 얻을 수 없습니다.")
                }
            }
    }
}


