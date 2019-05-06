package lutas.sample.linetvhomequiz.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import lutas.sample.linetvhomequiz.model.DramaEntity

@Database(entities = [(DramaEntity::class)], version = 1)
abstract class MyDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "db"
    }

    abstract fun getDramaDao(): DramaDao
}