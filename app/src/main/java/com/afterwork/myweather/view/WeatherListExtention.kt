package com.afterwork.myweather.view

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.myweather.di.API_BASE_URL
import com.afterwork.myweather.network.response.LocationWeather
import com.afterwork.myweather.viewmodel.WeatherViewModel
import com.facebook.drawee.view.SimpleDraweeView


@BindingAdapter("setWeatherIconView")
fun setWeatherIconView(imageView: SimpleDraweeView, iconName: String) {

    imageView.setImageURI("${API_BASE_URL}/static/img/weather/png/${iconName}.png")
}

@BindingAdapter(value = ["locationWeathers", "viewModel"])
fun setWeatherActivityAdapter(view: RecyclerView, items: List<LocationWeather>, vm: WeatherViewModel) {
    Log.d("WeatherListExtention", "WeatherListExtention - setAdapter")

    view.adapter?.run {
        if (this is WeatherViewAdapter) {
            Log.d("WeatherListExtention", "WeatherListExtention - setAdapter 1")
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        Log.d("WeatherListExtention", "WeatherListExtention - setAdapter 2")
        WeatherViewAdapter(items, vm)
            .apply { view.adapter = this }
    }
}
