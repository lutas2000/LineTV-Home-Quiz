package lutas.sample.linetvhomequiz.mobile

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_drama.*
import lutas.sample.linetvhomequiz.R
import lutas.sample.linetvhomequiz.mobile.ext.observe
import lutas.sample.linetvhomequiz.mobile.ext.observeVisible
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 單一劇集資訊頁面
 */
class DramaFragment: Fragment() {

    private val mViewModel by viewModel<DramaViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drama, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments?.let { DramaFragmentArgs.fromBundle(it) }
        mViewModel.refresh(args?.dramaId, args?.drama)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        mViewModel.apply {
            observeVisible(isLoading, progress)
            observe(error) {
                tv_error.visibility = if (it == null) View.GONE else View.VISIBLE
            }
            observe(drama) {
                it?.apply {
                    layout_content.visibility = View.VISIBLE
                    tv_title.text = name
                    tv_created_at.text = DataUtil().parseIso8601Date(createdAt)
                    tv_views.text = DataUtil().parseNumWithSuffix(totalViews)
                    tv_rating.text = String.format("%.1f", rating)
                    Glide.with(context!!)
                        .load(thumb)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // 僅儲存原始Thumb
                        .centerCrop()
                        .into(img_banner)
                }
            }
        }
    }

    override fun onDestroy() {
        mViewModel.destroy()
        super.onDestroy()
    }
}