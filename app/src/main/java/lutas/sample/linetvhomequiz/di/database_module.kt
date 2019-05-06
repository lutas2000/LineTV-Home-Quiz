package lutas.sample.linetvhomequiz.di

import android.arch.persistence.room.Room
import android.content.Context
import lutas.sample.linetvhomequiz.local.MyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { createDatabase(androidContext()) }
    factory { get<MyDatabase>().getDramaDao() }
}

fun createDatabase(context: Context): MyDatabase {
    return Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.DATABASE_NAME)
        .build()
}