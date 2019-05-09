package lutas.sample.linetvhomequiz.mobile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lutas.sample.linetvhomequiz.model.DramaEntity
import lutas.sample.linetvhomequiz.repository.DramaRepository

class DramaListViewModel(private val dramaRepository: DramaRepository): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val dramaList = MutableLiveData<List<DramaEntity>>()

    private val disposables = CompositeDisposable()

    init {
        isLoading.value = false
    }

    fun refresh() {
        isLoading.value = true
        val disposable = dramaRepository.getList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe (
                { list ->
                    isLoading.value = false
                    dramaList.value = list
                },
                { e ->
                    isLoading.value = false
                    Log.e("test", "refresh ${e.localizedMessage}")
                }
            )
        disposables.add(disposable)
    }

    /**
     * handle lifecycle destroy
     */
    fun destroy() {
        disposables.dispose()
    }
}