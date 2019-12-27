package com.afterwork.myweather.model

import com.afterwork.myweather.network.WeatherService
import com.afterwork.myweather.network.response.LocationResponse
import com.afterwork.myweather.network.response.LocationWeatherResponse
import io.reactivex.Single

class DataModelImpl(private val service: WeatherService): DataModel {
    override fun getLocationList(query: String): Single<List<LocationResponse>> {
        return service.getLocationList(query = query)
    }

    override fun getLocationWeather(woeid: Int): Single<LocationWeatherResponse> {
        return service.getLocationWeather(woeid = woeid)
    }
}