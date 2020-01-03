package com.afterwork.myweather.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.myweather.R
import com.afterwork.myweather.databinding.ItemWeatherBinding
import com.afterwork.myweather.network.response.LocationWeather
import com.afterwork.myweather.view.base.BindingViewHolder
import com.afterwork.myweather.viewmodel.WeatherViewModel


class WeatherViewAdapter(var items: List<LocationWeather> = arrayListOf(), val vm: WeatherViewModel)
    : RecyclerView.Adapter<WeatherViewAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(view: View) : BindingViewHolder<ItemWeatherBinding>(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        Log.d("WeatherViewAdapter", "onCreateViewHolder() - _viewType : $viewType")

        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_weather,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.weatherItem = items[position]
        holder.binding.vmMain = vm
    }
}