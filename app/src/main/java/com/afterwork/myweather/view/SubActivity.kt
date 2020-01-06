package com.afterwork.myweather.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.afterwork.myweather.R
import com.afterwork.myweather.base.BaseKotlinActivity
import com.afterwork.myweather.databinding.ActivitySubBinding
import com.afterwork.myweather.network.response.LocationWeather
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SubActivity: BaseKotlinActivity<ActivitySubBinding>() {

    companion object {
        val KEY_WEATHER = "weather"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_sub

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmSub = getViewModel()
        viewDataBinding.lifecycleOwner = this

        if(intent.hasExtra(KEY_WEATHER)){
            viewDataBinding.vmSub?.weather?.value = intent.getParcelableExtra<LocationWeather>(KEY_WEATHER)
        }
    }

}