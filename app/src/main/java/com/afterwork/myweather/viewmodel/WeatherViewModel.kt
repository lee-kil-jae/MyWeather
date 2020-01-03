package com.afterwork.myweather.viewmodel

import android.util.Log
import com.afterwork.myweather.base.BaseKotlinViewModel
import com.afterwork.myweather.model.DataModel
import com.afterwork.myweather.network.response.LocationWeather
import com.afterwork.myweather.util.NotNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherViewModel (private val model: DataModel) : BaseKotlinViewModel() {

    companion object {
        val TAG = "WeatherViewModel"
    }

    var keyword = ""
    var remainCount = 0

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _visibility: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val visibility: NotNullMutableLiveData<Boolean>
        get() = _visibility

    private val _locationWeathers: NotNullMutableLiveData<MutableList<LocationWeather>> = NotNullMutableLiveData(mutableListOf())
    val locationWeathers: NotNullMutableLiveData<MutableList<LocationWeather>>
        get() = _locationWeathers

    val _postMsg: NotNullMutableLiveData<String> = NotNullMutableLiveData("WeatherViewModel Injected.")
    val postMsg: NotNullMutableLiveData<String>
        get() = _postMsg

    fun getLocationWeather(woeid: Int){
        addDisposable(model.getLocationWeather(woeid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                it.run {
                    Log.d(TAG, "getLocationWeather: ${it.title}")
                    locationWeathers.value.add(this)
                    if(--remainCount <= 0){
                        stopProgress()

                    }
                }
            }, {
                Log.d(TAG, "getLocationWeather error, message : ${it.message}")
                if(--remainCount <= 0){
                    stopProgress()
                }
            }
            ))
    }

    fun onSearch() {
        if(keyword.length <= 0) {
            postMsg.postValue("키워드를 입력해주세요.")
            return
        }

        remainCount = 0
        startProgress()

        addDisposable(model.getLocationList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                it.run {
                    Log.d(TAG, "getLocationList size: ${it.size}")
                    if(it.isEmpty()) {
                        stopProgress()
                        postMsg.postValue("검색된 결과가 없습니다.")
                    }else {
                        remainCount = it.size
                        for (lo in it) {
                            getLocationWeather(lo.woeid)
                        }
                    }
                }
            }, {
                Log.d(TAG, "getLocationList error, message : ${it.message}")
                postMsg.postValue("${it.message}")
            }
            ))
    }

    fun startProgress(){
        visibility.value = false
        refreshing.value = true
    }
    fun stopProgress(){
        visibility.value = true
        refreshing.value = false
    }

    fun changeTempString(temp: Double): String {
        return "${temp.toInt()}°C"
    }

    fun changeHumidityString(humidity: Int): String {
        return "${humidity}%"
    }

}