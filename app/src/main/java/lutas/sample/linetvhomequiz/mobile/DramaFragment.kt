package lutas.sample.linetvhomequiz.mobile

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lutas.sample.linetvhomequiz.R

/**
 * 單一劇集資訊頁面
 */
class DramaFragment: Fragment() {

    lateinit var args: DramaFragmentArgs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            args = DramaFragmentArgs.fromBundle(it)
            Log.d("test", "args: ${args.drama}")
        }
    }
}