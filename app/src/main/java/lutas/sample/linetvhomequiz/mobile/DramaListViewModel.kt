package lutas.sample.linetvhomequiz.mobile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lutas.sample.linetvhomequiz.model.DramaEntity
import lutas.sample.linetvhomequiz.repository.DramaRepository

class DramaListViewModel(private val dramaRepository: DramaRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error
    private val _dramaList = MutableLiveData<List<DramaEntity>>()
    val dramaList: LiveData<List<DramaEntity>>
        get() = _dramaList

    private val disposables = CompositeDisposable()

    init {
        _isLoading.value = false
    }

    fun refresh() {
        _isLoading.value = true
        val disposable = dramaRepository.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { list ->
                    _dramaList.value = list
                    _isLoading.value = false
                },
                { e ->
                    _error.value = e
                    _isLoading.value = false
                }
            )
        disposables.add(disposable)
    }

    /**
     * handle lifecycle destroy
     */
    fun destroy() {
        disposables.clear()
    }
}