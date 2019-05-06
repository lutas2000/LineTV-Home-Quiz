package lutas.sample.linetvhomequiz.di

import lutas.sample.linetvhomequiz.remote.DramaService
import lutas.sample.linetvhomequiz.repository.DramaRepository
import lutas.sample.linetvhomequiz.mobile.DramaListViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    viewModel { DramaListViewModel(get()) }

    factory { DramaRepository(get(), get()) }
    single { createOkHttpClient() }
    single { createService<DramaService>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        // TODO use config
        .baseUrl("http://www.mocky.io/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}