package lutas.sample.linetvhomequiz.local

import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import lutas.sample.linetvhomequiz.model.DramaEntity

@Dao
interface DramaDao {

    @Query("SELECT * FROM " + DramaEntity.TABLE_NAME)
    fun getAll(): Single<List<DramaEntity>>

    @Query("SELECT * FROM " + DramaEntity.TABLE_NAME + " WHERE dramaId=:dramaId")
    fun getDrama(dramaId: Int): Single<DramaEntity>

    @Query("DELETE FROM " + DramaEntity.TABLE_NAME)
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(dramaList: List<DramaEntity>)
}