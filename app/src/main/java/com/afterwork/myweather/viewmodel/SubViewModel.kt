package com.afterwork.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import com.afterwork.myweather.base.BaseKotlinViewModel
import com.afterwork.myweather.model.DataModel
import com.afterwork.myweather.network.response.LocationWeather

class SubViewModel (private val model: DataModel) : BaseKotlinViewModel() {

    private val _weather: MutableLiveData<LocationWeather> = MutableLiveData()
    val weather: MutableLiveData<LocationWeather>
        get() = _weather
}