package com.example.gnumoa_hayeon

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
    private val listData: ArrayList<String>,
    items: ArrayList<MajorActivity.Recycler_item>
) : RecyclerView.Adapter<Major_RecyclerViewAdapter.ViewHolder>()  {
    var adapter: Second_Recyclerview_Adapter? = null
    private val items: ArrayList<MajorActivity.Recycler_item>
    private var context: Context? = null
    private val selectedItems = SparseBooleanArray()
    private var prePosition = -1

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.major_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Second_Recyclerview_Adapter(items)
        holder.recyclerView.adapter = adapter
        holder.onBind()
        holder.textView1.text = listData[position]
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
            textView1 = itemView.findViewById<TextView>(R.id.major_title)
            recyclerView = itemView.findViewById(R.id.second_recyclerview)

//            recyclerView.isNestedScrollingEnabled = true

        }

        fun onBind() {
            position = adapterPosition
            changeVisibility(selectedItems[position])
        }


        private fun changeVisibility(isExpanded: Boolean) {
            val dpValue = 250
            val d = context!!.resources.displayMetrics.density
            val height = (dpValue * d).toInt()

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
