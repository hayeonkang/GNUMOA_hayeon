import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gnumoa_hayeon.MajorActivity
import com.example.gnumoa_hayeon.R

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
        val item = items[position]
        holder.title.text = item.title
        holder.heart.setOnClickListener {
            // Heart 버튼 클릭 이벤트 처리
            holder.heart.setBackgroundResource(R.drawable.full_heart)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.no_image)
        var title: TextView = itemView.findViewById(R.id.cardview_title)
        var heart: Button = itemView.findViewById(R.id.cardview_heart)
    }
}
