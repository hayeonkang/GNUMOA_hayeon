package com.example.gnumoa_hayeon

import Second_Recyclerview_Adapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Major_RecyclerViewAdapter(
    private val listData: ArrayList<MajorActivity.Recycler_item_out>
) : RecyclerView.Adapter<Major_RecyclerViewAdapter.ViewHolder>() {
    private val context: Context? = null
    private val selectedItems = SparseBooleanArray()
    private var prePosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.major_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        val adapter = Second_Recyclerview_Adapter(listData[position].items)
        holder.recyclerView.adapter = adapter
        holder.onBind(itemCount)
        holder.textView1.text = listData[position].name
        holder.textView1.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (selectedItems[adapterPosition]) {
                selectedItems.delete(adapterPosition)
            } else {
                selectedItems.delete(prePosition)
                selectedItems.put(adapterPosition, true)
            }
            if (prePosition != -1) notifyItemChanged(prePosition)
            notifyItemChanged(adapterPosition)
            prePosition = adapterPosition
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView
        val recyclerView: RecyclerView
        private var position = 0

        init {
            textView1 = itemView.findViewById(R.id.major_title)
            recyclerView = itemView.findViewById(R.id.second_recyclerview)
        }

        fun onBind(itemCount: Int) {
            position = adapterPosition
            changeVisibility(selectedItems[position])
        }

        private fun changeVisibility(isExpanded: Boolean) {
            recyclerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            val height = recyclerView.measuredHeight

            val va =
                if (isExpanded) ValueAnimator.ofInt(0, height) else ValueAnimator.ofInt(height, 0)
            va.duration = 600
            va.addUpdateListener { animation ->
                val value = animation.animatedValue as Int
                recyclerView.layoutParams.height = value
                recyclerView.requestLayout()
                recyclerView.visibility =
                    if (isExpanded) View.VISIBLE else View.GONE
            }
            va.start()
        }
    }
}
