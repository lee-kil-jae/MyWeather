package com.afterwork.myweather.model

import com.afterwork.myweather.network.response.Location
import com.afterwork.myweather.network.response.LocationWeather
import io.reactivex.Single

interface DataModel {
    fun getLocationList(query: String): Single<List<Location>>

    fun getLocationWeather(path: Int): Single<LocationWeather>
}
