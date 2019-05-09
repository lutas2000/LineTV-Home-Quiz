package lutas.sample.linetvhomequiz.mobile

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import lutas.sample.linetvhomequiz.model.DramaEntity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import lutas.sample.linetvhomequiz.R

class DramaListAdapter(
    var list: List<DramaEntity>?
): RecyclerView.Adapter<DramaListAdapter.DramaViewHolder>() {

    class DramaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgThumb: ImageView = view.findViewById(R.id.img_thumb)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCreateAt: TextView = view.findViewById(R.id.tv_created_at)
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
            tvCreateAt.text = DataUtil().parseIso8601Date(drama.createdAt)
            tvRating.text = String.format("%.1f", drama.rating)
            showImage(itemView.context, imgThumb, drama.thumb)
            itemView.setOnClickListener {
                val controller = Navigation.findNavController(it)
                val action = DramaListFragmentDirections.toDramaFragment()
                action.drama = drama
                controller.navigate(action)
            }
        }
    }

    private fun showImage(context: Context, imgView: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // 僅儲存原始Thumb
            .into(imgView)
    }
}