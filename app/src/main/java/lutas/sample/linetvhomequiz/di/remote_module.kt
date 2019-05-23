package lutas.sample.linetvhomequiz.di

import com.google.gson.Gson
import lutas.sample.linetvhomequiz.Config
import lutas.sample.linetvhomequiz.remote.DramaService
import lutas.sample.linetvhomequiz.remote.ResponseConverterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {
    single { createRetrofit() }
    factory { createService<DramaService>(get()) }
}

private inline fun <reified T> createService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

private fun createRetrofit(): Retrofit {
    val gson = Gson()
    val okHttpClient = createOkHttpClient()
    return Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ResponseConverterFactory(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .build()
}
