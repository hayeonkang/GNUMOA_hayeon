package com.example.gnumoa_hayeon

data class Majorlist(
    val single_major: String,
    val single_major_img: Int? = null,
    val each_major: MutableList<Submajor> = mutableListOf() // 각 메인 메이저에 대한 서브 메이저 리스트
)




