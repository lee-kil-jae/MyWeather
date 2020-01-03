package com.afterwork.myweather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afterwork.myweather.base.BaseKotlinViewModel
import com.afterwork.myweather.model.DataModel
import com.afterwork.myweather.network.response.LocationResponse
import com.afterwork.myweather.network.response.LocationWeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val model: DataModel) : BaseKotlinViewModel() {

    companion object {
        val TAG = "MainViewModel"
    }

    private val _locationWeatherResponseLiveData = MutableLiveData<LocationWeatherResponse>()
    val locationWeatherResponseLiveData:LiveData<LocationWeatherResponse>
        get() = _locationWeatherResponseLiveData

    fun getLocationList(query: String) {
        addDisposable(model.getLocationList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                it.run {
                    Log.d(TAG, "getLocationList size: ${it.size}")
//                    _locationListResponseLiveData.postValue(this)
                    for(lo in it){
                        getLocationWeather(lo.woeid)
                    }
                }
            }, {
                Log.d(TAG, "getLocationList error, message : ${it.message}")
            }
            ))
    }

    fun getLocationWeather(woeid: Int){
        addDisposable(model.getLocationWeather(woeid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                it.run {
                    Log.d(TAG, "getLocationWeather: ${it.title}")
                    _locationWeatherResponseLiveData.postValue(this)

                }
            }, {
                Log.d(TAG, "getLocationWeather error, message : ${it.message}")
            }
            ))
    }
}