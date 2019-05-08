package lutas.sample.linetvhomequiz.mobile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import lutas.sample.linetvhomequiz.model.DramaEntity
import android.view.LayoutInflater
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import lutas.sample.linetvhomequiz.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DramaListAdapter(
    var list: List<DramaEntity>?,
    val test: () -> Unit
): RecyclerView.Adapter<DramaListAdapter.DramaViewHolder>() {

    class DramaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgThumb: ImageView = view.findViewById(R.id.img_thumb)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCreateAt: TextView = view.findViewById(R.id.tv_created_at)
        val tvViews: TextView = view.findViewById(R.id.tv_views)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DramaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drama, parent, false)
        return DramaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: DramaViewHolder, position: Int) {
        val drama = list?.get(position) ?: return
        viewHolder.apply {
            tvName.text = drama.name
            tvCreateAt.text = formatTime(drama.createdAt)
            tvViews.text = withSuffix(drama.totalViews)
            tvRating.text = String.format("%.1f", drama.rating)
            showImage(itemView.context, imgThumb, drama.thumb)
            itemView.setOnClickListener {
                test()
            }
        }
    }

    private fun formatTime(time: String?): String {
        if (time == null) return ""
        return try {
            // TODO 確認是否正確
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            val date = inputFormat.parse(time)
            outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }

    private fun withSuffix(count: Long): String {
        if (count < 1000) return "$count"
        val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            count / Math.pow(1000.0, exp.toDouble()),
            "kMGTPE"[exp - 1]
        )
    }

    private fun showImage(context: Context, imgView: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // 僅儲存原始Thumb
            .into(imgView)
    }
}