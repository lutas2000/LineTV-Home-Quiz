package lutas.sample.linetvhomequiz.mobile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lutas.sample.linetvhomequiz.model.DramaEntity
import lutas.sample.linetvhomequiz.repository.DramaRepository
import java.lang.IllegalArgumentException

class DramaViewModel(
    private val dramaRepository: DramaRepository
): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error
    private val _drama = MutableLiveData<DramaEntity>()
    val drama: LiveData<DramaEntity>
        get() = _drama

    private val disposables = CompositeDisposable()

    fun refresh(dramaId: Int?, data: DramaEntity?) {
        if (data != null) {
            _drama.value = data
            return
        }
        if (dramaId == null || dramaId < 0) {
            _error.value = IllegalArgumentException()
            return
        }

        _isLoading.value = true
        val disposable = dramaRepository.getDrama(dramaId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { obj ->
                    _isLoading.value = false
                    _drama.value = obj
                },
                { e ->
                    _isLoading.value = false
                    _error.value = e
                    e.printStackTrace()
                }
            )
        disposables.add(disposable)
    }

    fun destroy() {
        disposables.dispose()
    }
}