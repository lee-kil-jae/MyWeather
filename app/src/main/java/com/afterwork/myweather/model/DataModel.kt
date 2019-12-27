package com.afterwork.myweather.model

import com.afterwork.myweather.network.response.LocationResponse
import com.afterwork.myweather.network.response.LocationWeatherResponse
import io.reactivex.Single

interface DataModel {
    fun getLocationList(query: String): Single<List<LocationResponse>>

    fun getLocationWeather(path: Int): Single<LocationWeatherResponse>
}
