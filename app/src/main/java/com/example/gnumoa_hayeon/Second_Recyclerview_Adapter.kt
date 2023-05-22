package com.example.gnumoa_hayeon

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson


object SharedDB2 {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("MajorPost", Context.MODE_PRIVATE)
    }

    fun getInstance(): SharedPreferences {
        if(!this::sharedPreferences.isInitialized) {
            throw java.lang.IllegalStateException("SharedPreferencesSingleton is not initialized")
        }
        return sharedPreferences
    }
}
class Second_Recyclerview_Adapter(
    private val items: MutableList<MajorActivity.Recycler_item>,
    private val heartMajorList: HeartMajorList
) : RecyclerView.Adapter<Second_Recyclerview_Adapter.ViewHolder>() {
    var context: Context? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_major_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items.get(position).title
        holder.getInit(items.get(position).title)
        holder.bind(items[position], holder.heart)
    }


    fun serializeData(data: Any): String {
        val gson = Gson()
        return gson.toJson(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var imageView: ImageView = itemView.findViewById(R.id.no_image)
        var title: TextView = itemView.findViewById(R.id.cardview_title)
        var heart: AppCompatImageButton = itemView.findViewById(R.id.cardview_heart)

        init {
            SharedDB2.init(itemView.context)
        }
        private val ChangeMajorInfo: SharedPreferences = SharedDB2.getInstance()
        fun getInit(title: String) {
            val key = title
            //Log.d("key",key)
            if (ChangeMajorInfo.contains(key)) {
                heart.setBackgroundResource(R.drawable.full_heart)
            }
        }

        fun bind(majorItems: MajorActivity.Recycler_item, HeartButton: ImageButton) {
            HeartButton.setOnClickListener {
                val item = majorItems

                val MajorEditor = ChangeMajorInfo.edit()
                val serializeData = serializeData(item)
                val nametitle = item.title

                val db = Firebase.firestore


                FirebaseMessaging.getInstance().token
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val token = task.result
                            val tokenDocument = db.collection("Tokens").document(token)
                            // 데이터 추가 또는 삭제 작업을 수행합니다.
                            if (ChangeMajorInfo.contains(nametitle)) {
                                heart.setBackgroundResource(R.drawable.empty_heart)
                                MajorEditor.remove(nametitle)
                                removeData(tokenDocument, nametitle)
                            } else {
                                heart.setBackgroundResource(R.drawable.full_heart)
                                MajorEditor.putString(nametitle, serializeData)
                                saveData(tokenDocument, nametitle, serializeData)
                            }

                            MajorEditor.apply()
                        }
                    }
            }
        }
        private fun removeData(document: DocumentReference, key: String) {
            document.update(key, "")
                .addOnFailureListener { e ->
                    Log.w("FCM Token", "Firestore에서 데이터 삭제 실패", e)
                }
        }

        private fun saveData(document: DocumentReference, key: String, value: String) {
            document.update(key, value)
                .addOnFailureListener { e ->
                    Log.w("FCM Token", "Firestore에 데이터 저장 실패", e)
                }
        }
    }
}
