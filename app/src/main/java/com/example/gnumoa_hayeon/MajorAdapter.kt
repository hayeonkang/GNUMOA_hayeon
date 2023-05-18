package com.example.gnumoa_hayeon

import android.bluetooth.BluetoothClass
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MajorAdapter : RecyclerView.Adapter<MajorAdapter.MajorViewHolder>() {

    val majorList = arrayListOf(
        Major_list("인문대학"),
        Major_list("사회과학대학"),
        Major_list("자연과학대학"),
        Major_list("경영대학"),
        Major_list("공과대학"),
        Major_list("농업생명과학대학"),
        Major_list("법과대학"),
        Major_list("사범대학"),
        Major_list("수의과대학"),
        Major_list("의과대학"),
        Major_list("간호대학"),
        Major_list("해양과학대학"),
        Major_list("약학대학"),
        Major_list("본부대학 I"),
        Major_list("건설환경공과대학"),
        Major_list("융합기술공과대학"),
        Major_list("본부대학")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MajorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.major_list, parent, false)
        return MajorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MajorViewHolder, position: Int) {
        holder.singlemajor.text = majorList.get(position).name
        holder.itemView.setOnClickListener {
            // 클릭 이벤트 처리
        }
    }

    override fun getItemCount(): Int = majorList.size

    //화면에 표시될 아이템 뷰 저장하는 객체
    //공지박스 하나가 한개의 Holder, 하나의 홀더 안에 들어갈 필드 값들 명시
    inner class MajorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val singlemajor = itemView.findViewById<TextView>(R.id.major_title) // 학과
    }
}
