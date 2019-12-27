package com.afterwork.myweather.network

import com.afterwork.myweather.network.response.LocationResponse
import com.afterwork.myweather.network.response.LocationWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("/api/location/search/")
    fun getLocationList(@Query("query") query: String): Single<List<LocationResponse>>

    @GET("/api/location/{woeid}")
    fun getLocationWeather(@Path("woeid") woeid: Int): Single<LocationWeatherResponse>
}