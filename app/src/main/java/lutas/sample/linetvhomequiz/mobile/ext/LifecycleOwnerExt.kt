package lutas.sample.linetvhomequiz.mobile.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.view.View

fun<T> LifecycleOwner.observe(data: LiveData<T>, onChanged: (T?) -> Unit) {
    data.observe(this, Observer<T> { onChanged(it) })
}

fun LifecycleOwner.observeVisible(data: LiveData<Boolean>, view: View) {
    data.observe(this, Observer<Boolean> {
        println("observeVisible")
        view.visibility = if (it == true) View.VISIBLE else View.GONE
    })
}

