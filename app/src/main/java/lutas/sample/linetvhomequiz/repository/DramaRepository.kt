package lutas.sample.linetvhomequiz.repository

import android.arch.persistence.room.EmptyResultSetException
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lutas.sample.linetvhomequiz.local.DramaDao
import lutas.sample.linetvhomequiz.model.DramaEntity
import lutas.sample.linetvhomequiz.remote.DramaService

class DramaRepository(
    private val dramaService: DramaService,
    private val dramaDao: DramaDao
) {

    fun getList(): Single<List<DramaEntity>?> {
        return dramaService.getList()
            .doOnSuccess { list -> // request成功時儲存到database
                list?.let {
                    dramaDao.deleteAll()
                    dramaDao.saveAll(it)
                }
            }
            .onErrorResumeNext {  // request Error時改用local data
                it.printStackTrace()
                dramaDao.getAll()
            }
    }

    fun getDrama(dramaId: Int): Single<DramaEntity> {
        return dramaDao.getDrama(dramaId)
            .onErrorResumeNext { e ->
                if (e is EmptyResultSetException) {
                    dramaService.getList()
                        .flatMapObservable { Observable.fromIterable(it) }
                        .filter { it.dramaId == dramaId }
                        .firstOrError()
                } else {
                    throw e
                }
            }
    }
}