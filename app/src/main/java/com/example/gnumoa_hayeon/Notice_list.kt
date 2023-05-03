package com.example.gnumoa_hayeon

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Parcelable
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize
import org.junit.Ignore


//이미지 리소스들 set 해줄때는 이미지 파일들을 Int형태로 넣어줘야 하기 때문에 정수타입으로 선언
//변수 타입은 Firestore 필드의 타입과 같게 한다
//date 타입 String -> Timestamp 로 변경
@Parcelize
data class Notice_list(
    val major: String?=null,
    val category: String?=null,
    val title: String?=null,
    val context: ArrayList<String>?=null,
    val createdAt: Timestamp?=null,
    val heart: Int?=null,
    val baseUrl: String?=null,
    val fileUrls: HashMap<String, String>?=null,
    val html: String?=null
) : Parcelable
//{
//
//
//    companion object {
//        fun fromDocument(snapshot: DocumentSnapshot): Notice_list {
//            val data = snapshot.data ?: throw IllegalArgumentException("Document doesn't exist!")
//            return Notice_list(
//                major = data["major"] as String?,
//                category = data["category"] as String?,
//                title = data["title"] as String?,
//                context = data["context"] as ArrayList<String>?,
//                createdAt = data["createdAt"] as Timestamp?,
//                heart = data["heart"] as Int?
//            )
//        }
//    }
//}
//
