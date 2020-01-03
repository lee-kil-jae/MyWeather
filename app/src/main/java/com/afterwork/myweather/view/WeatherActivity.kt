package com.afterwork.myweather.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.afterwork.myweather.base.BaseKotlinActivity
import com.afterwork.myweather.R
import com.afterwork.myweather.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WeatherActivity : BaseKotlinActivity<ActivityMainBinding>() {

    private val TAG = "WeatherActivity"

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vmMain = getViewModel()
        viewDataBinding.setLifecycleOwner(this)
        viewDataBinding.getVmMain()?.postMsg?.observe(this, Observer<String> {
            Toast.makeText(this@WeatherActivity, it, Toast.LENGTH_SHORT).show()
        })
    }

}
