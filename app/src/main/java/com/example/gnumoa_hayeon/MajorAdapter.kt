package com.example.gnumoa_hayeon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MajorAdapter(
    val majorList: List<Majorlist>,
    val subMajorAdapter: SubMajorAdapter // Accept an instance of the SubMajorAdapter
) : RecyclerView.Adapter<MajorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.major_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val major = majorList[position]
        holder.bind(major)
    }

    override fun getItemCount() = majorList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val majorTitleTextView: TextView = itemView.findViewById(R.id.major_title)
        private val eachmajorTextView: TextView = itemView.findViewById(R.id.each_major_txt) //
        private val subMajorRecyclerView: RecyclerView = itemView.findViewById(R.id.sub_major_recycler_view)

        fun bind(major: Majorlist) {
            majorTitleTextView.text = major.single_major

            //
            val subMajorNames = major.each_major.joinToString(", ") { it.sub_major_name ?: "" }
            eachmajorTextView.text = subMajorNames
           //


            subMajorRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            subMajorRecyclerView.adapter = subMajorAdapter // Use the passed SubMajorAdapter instance
            //subMajorAdapter.updateData(major.each_major ?: emptyList()) // Update the data for the adapter
        }
    }
}
