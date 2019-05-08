package lutas.sample.linetvhomequiz.di

import com.google.gson.Gson
import lutas.sample.linetvhomequiz.remote.DramaService
import lutas.sample.linetvhomequiz.remote.ResponseConverterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {
    single { createOkHttpClient() }
    single { createWrappedService<DramaService>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWrappedService(okHttpClient: OkHttpClient): T {
    val gson = Gson()
    val retrofit = Retrofit.Builder()
        // TODO use config
        .baseUrl("http://www.mocky.io/v2/")
        .client(okHttpClient)
        .addConverterFactory(ResponseConverterFactory(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
