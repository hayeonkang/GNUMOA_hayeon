import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gnumoa_hayeon.HeartMajorList
import com.example.gnumoa_hayeon.MajorActivity
import com.example.gnumoa_hayeon.R

class Second_Recyclerview_Adapter(
    private val items: MutableList<MajorActivity.Recycler_item>,
    private val heartMajorList: HeartMajorList
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

        val isHeartFilled = heartMajorList.names.contains(item.title) // 해당 아이템이 리스트에 있는지 확인하여 상태를 설정합니다

        val heartResource = if (isHeartFilled) {
            R.drawable.full_heart // 상태에 따라 리소스를 선택합니다 (채워진 하트)
        } else {
            R.drawable.empty_heart // 상태에 따라 리소스를 선택합니다 (빈 하트)
        }
        holder.heart.setBackgroundResource(heartResource)

        holder.heart.setOnClickListener {
            val isSelected = !isHeartFilled // 클릭할 때마다 상태를 토글합니다

            if (isSelected) {
                heartMajorList.names.add(item.title)
            } else {
                heartMajorList.names.remove(item.title)
            }
            notifyItemChanged(position)
            Log.d("HeartMajor_list", "Updated name: ${heartMajorList.names}")
        }
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var imageView: ImageView = itemView.findViewById(R.id.no_image)
        var title: TextView = itemView.findViewById(R.id.cardview_title)
        var heart: AppCompatImageButton = itemView.findViewById(R.id.cardview_heart)
    }
}
