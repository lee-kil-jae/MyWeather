package com.afterwork.myweather.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.afterwork.myweather.base.BaseKotlinActivity
import com.afterwork.myweather.R
import com.afterwork.myweather.databinding.ActivityMainBinding
import com.afterwork.myweather.network.response.LocationWeather
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WeatherActivity : BaseKotlinActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmMain = getViewModel()
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.vmMain?.postMsg?.observe(this, Observer<String> {
            Toast.makeText(this@WeatherActivity, it, Toast.LENGTH_SHORT).show()
        })

        viewDataBinding.vmMain?.newActivity?.observe(this, Observer<LocationWeather> {
            val intent = Intent(this@WeatherActivity, SubActivity::class.java)
            intent.putExtra(SubActivity.KEY_WEATHER, it)
            startActivity(intent)
        })
    }

}
