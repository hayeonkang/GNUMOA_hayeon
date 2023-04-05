package com.example.gnumoa_hayeon

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


//이미지 리소스들 set 해줄때는 이미지 파일들을 Int형태로 넣어줘야 하기 때문에 정수타입으로 선언
//변수 타입은 Firestore 필드의 타입과 같게 한다
//date 타입 String -> Timestamp 로 변경
data class Notice_list(
    val major: String,
    val category: String,
    val title: String,
    val context: String,
    val date: String,
    val heart: Int)


