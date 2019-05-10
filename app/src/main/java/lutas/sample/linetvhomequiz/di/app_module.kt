package lutas.sample.linetvhomequiz.di

import com.google.gson.Gson
import lutas.sample.linetvhomequiz.remote.DramaService
import lutas.sample.linetvhomequiz.repository.DramaRepository
import lutas.sample.linetvhomequiz.mobile.DramaListViewModel
import lutas.sample.linetvhomequiz.mobile.DramaViewModel
import lutas.sample.linetvhomequiz.remote.ResponseConverterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    viewModel { DramaListViewModel(get()) }
//    viewModel { DramaViewModel(get(), parametersOf(DramaViewModel)) }
    viewModel { DramaViewModel(get())  }

    factory { DramaRepository(get(), get()) }
}