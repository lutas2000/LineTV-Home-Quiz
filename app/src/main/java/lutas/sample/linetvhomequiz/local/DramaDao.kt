package lutas.sample.linetvhomequiz.local

import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import lutas.sample.linetvhomequiz.model.DramaEntity

@Dao
interface DramaDao {

    @Query("select * from " + DramaEntity.TABLE_NAME)
    fun getAll(): Single<List<DramaEntity>>

    @Query("DELETE FROM " + DramaEntity.TABLE_NAME)
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(dramaList: List<DramaEntity>)
}