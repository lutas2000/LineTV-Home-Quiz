package lutas.sample.linetvhomequiz.mobile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_drama_list.*
import lutas.sample.linetvhomequiz.R
import lutas.sample.linetvhomequiz.mobile.ext.observe
import lutas.sample.linetvhomequiz.mobile.ext.observeVisible
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 戲劇列表
 */
class DramaListFragment: Fragment() {

    private val mViewModel: DramaListViewModel by viewModel()
    private lateinit var adapter: DramaListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drama_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        tv_message.setOnClickListener {
            it.visibility = View.GONE
            mViewModel.refresh()
        }
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = DramaListAdapter(emptyList())
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun observeData() {
        mViewModel.apply {
            observeVisible(isLoading, progress)
            observe(error) {
                tv_message.visibility = if (it == null) View.GONE else View.VISIBLE
            }
            observe(dramaList) {
                if (it.isNullOrEmpty()) {
                    showApiError()
                } else {
                    adapter.list = it
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun showApiError() {
        tv_message.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        mViewModel.destroy()
        super.onDestroy()
    }
}