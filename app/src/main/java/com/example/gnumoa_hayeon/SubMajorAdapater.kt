package com.example.gnumoa_hayeon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class SubMajorAdapter : RecyclerView.Adapter<SubMajorAdapter.SubMajorViewHolder>() {

    var submajorList: ArrayList<Submajor> = arrayListOf()

    // 뷰 홀더를 생성하고 뷰를 붙여주는 부분
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMajorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_major_list, parent, false)
        return SubMajorViewHolder(view)
    }

    // 생성된 뷰 홀더에 데이터를 바인딩하는 부분
    override fun onBindViewHolder(holder: SubMajorViewHolder, position: Int) {
        val submajor = submajorList[position]
        holder.bind(submajor)
    }

    // 전체 데이터 개수를 반환하는 부분
    override fun getItemCount(): Int {
        return submajorList.size
    }

    // 뷰 홀더
    class SubMajorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val submajorName: TextView = itemView.findViewById(R.id.each_major_txt)
        private val subMajorImg: ImageView = itemView.findViewById(R.id.each_major_img)
        private val alarm: ImageView = itemView.findViewById(R.id.alarm)

        fun bind(submajor: Submajor) {
            submajorName.text = submajor.sub_major_name
            subMajorImg.setImageResource(submajor.sub_major_img)
            alarm.setImageResource(submajor.sub_major_alarm)
        }
    }

}

//package com.example.gnumoa_hayeon
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import java.util.ArrayList
//
//class SubMajorAdapter : RecyclerView.Adapter<SubMajorAdapter.SubMajorViewHolder>() {
//
//    var submajorList: ArrayList<Majorlist> = arrayListOf()
//
//    // 뷰 홀더를 생성하고 뷰를 붙여주는 부분
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMajorViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_major_list, parent, false)
//        return SubMajorViewHolder(view)
//    }
//
//    // 생성된 뷰 홀더에 데이터를 바인딩하는 부분
//    override fun onBindViewHolder(holder: SubMajorViewHolder, position: Int) {
//        holder.submajorName.text = submajorList.get(position).each_major.toString()
////        submajorList.get(position).major?.let { holder.subMajorImg.setImageResource(submajor.each_major_img) }
////        submajorList.get(position).major?.let { holder.alarm.setImageResource(alarm) }
//    }
//
//    // 전체 데이터 개수를 반환하는 부분
//    override fun getItemCount(): Int {
//        return submajorList.size
//    }
//
//    // 뷰 홀더
//    class SubMajorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//         val submajorName: TextView = itemView.findViewById(R.id.each_major_txt)
//         val subMajorImg: ImageView = itemView.findViewById(R.id.each_major_img)
//         val alarm: ImageView = itemView.findViewById(R.id.alarm)
//
//        fun bind(submajor: Submajor) {
//            submajorName.text = submajor.sub_major_name
//            subMajorImg.setImageResource(submajor.sub_major_img)
//            alarm.setImageResource(submajor.sub_major_alarm)
//        }
//    }
//
//}

