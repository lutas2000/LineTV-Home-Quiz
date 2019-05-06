package lutas.sample.linetvhomequiz.di

import lutas.sample.linetvhomequiz.remote.DramaService
import org.koin.dsl.module

val remoteModule = module {
    single { createOkHttpClient() }
    single { createService<DramaService>(get()) }
}

//fun createOkHttpClient(): OkHttpClient {
//    return OkHttpClient.Builder()
//        .connectTimeout(10L, TimeUnit.SECONDS)
//        .readTimeout(10L, TimeUnit.SECONDS)
//        .build()
//}
//
//inline fun <reified T> createService(okHttpClient: OkHttpClient): T {
//    val retrofit = Retrofit.Builder()
//        // TODO use config
//        .baseUrl("https://www.mocky.io/v2")
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
//    return retrofit.create(T::class.java)
//}
