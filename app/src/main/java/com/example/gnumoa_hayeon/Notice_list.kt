package com.example.gnumoa_hayeon


import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

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
    var heart: Boolean=false,
    val baseUrl: String?=null,
    val fileUrls: HashMap<String, String>?=null,
    val html: String?=null,
) : Parcelable

