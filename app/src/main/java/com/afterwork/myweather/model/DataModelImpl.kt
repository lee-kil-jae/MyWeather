package com.afterwork.myweather.model

import com.afterwork.myweather.network.WeatherService
import com.afterwork.myweather.network.response.Location
import com.afterwork.myweather.network.response.LocationWeather
import io.reactivex.Single

class DataModelImpl(private val service: WeatherService): DataModel {
    override fun getLocationList(query: String): Single<List<Location>> {
        return service.getLocationList(query = query)
    }

    override fun getLocationWeather(woeid: Int): Single<LocationWeather> {
        return service.getLocationWeather(woeid = woeid)
    }
}