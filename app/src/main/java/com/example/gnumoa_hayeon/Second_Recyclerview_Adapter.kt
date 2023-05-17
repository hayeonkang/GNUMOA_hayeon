package com.example.gnumoa_hayeon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Second_Recyclerview_Adapter(items: ArrayList<MajorActivity.Recycler_item>) :
    RecyclerView.Adapter<Second_Recyclerview_Adapter.ViewHolder>() {
    var context: Context? = null
    private val items: ArrayList<MajorActivity.Recycler_item>



    override fun getItemCount(): Int {
        return items.size
    }

    init {
        this.items = items

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_major_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var title: TextView
        var heart: Button
        init {
            imageView = itemView.findViewById(R.id.no_image)
            title = itemView.findViewById(R.id.cardview_title)
            heart = itemView.findViewById(R.id.cardview_heart)
        }
    }
}
