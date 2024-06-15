package com.example.customerlist.di


import com.example.customerlist.network.CustomerApiService
import com.example.customerlist.repository.repositoryimpl.CustomerListRepoImpl
import com.example.customerlist.utils.constant.ApiContant
import com.example.customerlist.viemodel.CustomerListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(ApiContant.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(CustomerApiService::class.java) }
}

val repositoryModule = module {
    single { CustomerListRepoImpl(get()) }
}

val viewModelModule = module {
    viewModel { CustomerListViewModel(get()) }
}

val appModule = listOf(networkModule, repositoryModule, viewModelModule)


