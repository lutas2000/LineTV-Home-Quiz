package lutas.sample.linetvhomequiz.repository

import android.util.Log
import io.reactivex.Single
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
            .map { it.data } // 解包data
            .doOnSuccess { list -> // request成功時儲存到database
                Log.d("test", "saveAll")
                list?.let {
                    dramaDao.deleteAll()
                    dramaDao.saveAll(it)
                }
            }
            .onErrorResumeNext {  // request Error時改用local data
                Log.e("test", "onErrorResumeNext ${it.localizedMessage}")
                dramaDao.getAll()
            }
    }
}