import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gnumoa_hayeon.MajorActivity
import com.example.gnumoa_hayeon.R
import com.google.gson.Gson

class Second_Recyclerview_Adapter(
    private val items: MutableList<MajorActivity.Recycler_item>
) : RecyclerView.Adapter<Second_Recyclerview_Adapter.ViewHolder>() {
    var context: Context? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_major_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items.get(position).title

        holder.getInit(items.get(position).title)
        holder.bind(items[position], holder.heart)
    }

    fun serializeData(data: Any): String {
        val gson = Gson()
        return gson.toJson(data)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var imageView: ImageView = itemView.findViewById(R.id.no_image)
        var title: TextView = itemView.findViewById(R.id.cardview_title)
        val heart: ImageButton = itemView.findViewById<ImageButton>(R.id.MajorHeart) // 관심목록


        fun getInit(title:String){
            val ChangeMajorInfo = itemView.context.getSharedPreferences("MajorPost", Context.MODE_PRIVATE)
            val key = title
            //Log.d("key",key)
            if (ChangeMajorInfo.contains(key)) {
                heart.setImageResource(R.drawable.full_heart)
            }
        }

        fun bind(majorItems: MajorActivity.Recycler_item, HeartButton: ImageButton) {
            HeartButton.setOnClickListener {
                val item = majorItems

                val ChangeMajorInfo = itemView.context.getSharedPreferences("MajorPost", Context.MODE_PRIVATE)
                val MajorEditor = ChangeMajorInfo.edit()
                val serializeData = serializeData(item)
                val key = item.title

                //MajorEditor.clear() //일단 초기화

                if (ChangeMajorInfo.contains(key)) {
                    heart.setImageResource(R.drawable.empty_heart)
                    //Log.d("key", key)
                    MajorEditor.remove(key) // 데이터 삭제
                    MajorEditor.apply()

                    val allEntries: Map<String, *> = ChangeMajorInfo.all
                    val dataSize = allEntries.size
                    //Log.d("dataSize", dataSize.toString())
                }else{
                    heart.setImageResource(R.drawable.full_heart)
                    MajorEditor.putString(key,serializeData) // 데이터 추가
                    //Log.d("key", key)
                    //Log.d("serializeData", serializeData)
                    MajorEditor.apply()

                    val allEntries: Map<String, *> = ChangeMajorInfo.all
                    val dataSize = allEntries.size
                   // Log.d("dataSize", dataSize.toString())
                }
            }
        }
    }
}
