package com.afterwork.myweather.di

import com.afterwork.myweather.model.DataModel
import com.afterwork.myweather.model.DataModelImpl
import com.afterwork.myweather.network.WeatherService
import com.afterwork.myweather.viewmodel.SubViewModel
import com.afterwork.myweather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        WeatherViewModel(get())
    }

    viewModel {
        SubViewModel(get())
    }
}

val API_BASE_URL = "https://www.metaweather.com"

var retrofitPart = module {
    single<WeatherService>{
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}

var myDiModule = listOf(
    retrofitPart,
    modelPart,
    viewModelPart
)
