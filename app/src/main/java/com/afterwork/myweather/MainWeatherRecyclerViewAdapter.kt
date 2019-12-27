package com.afterwork.myweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.myweather.di.API_BASE_URL
import kotlinx.android.synthetic.main.item_main.view.*

class MainWeatherRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    data class WeatherItem(
        val location_title:String,
        val today_ic:String,
        val today_weather:String,
        val today_temp:Int,
        val today_humidity:Int,
        val tomorrow_ic:String,
        val tomorrow_weather:String,
        val tomorrow_temp:Int,
        val tomorrow_humidity:Int)

    class WeatherHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)){
        fun onBind(item: WeatherItem){
            itemView.run{
                tv_local.text = item.location_title
                iv_today_weather.setImageURI("${API_BASE_URL}/static/img/weather/png/${item.today_ic}.png")
                tv_today_weather.text = item.today_weather
                tv_today_temp.text = "${item.today_temp}°C"
                tv_today_humidity.text = "${item.today_humidity}%"

                iv_tomor_weather.setImageURI("${API_BASE_URL}/static/img/weather/png/${item.tomorrow_ic}.png")
                tv_tomor_weather.text = item.tomorrow_weather
                tv_tomor_temp.text = "${item.tomorrow_temp}°C"
                tv_tomor_humidity.text = "${item.tomorrow_humidity}%"
            }
        }
    }

    private val weatherItems = ArrayList<WeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherHolder(parent)

    override fun getItemCount() = weatherItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? WeatherHolder)?.onBind(weatherItems[position])
    }

    fun addWeatherItem(title: String,
                       today_ic:String, today_weather: String, today_temp: Double, today_humidity: Int,
                       tomorrow_ic:String, tomorrow_weather: String, tomorrow_temp: Double, tomorrow_humidity: Int){
        weatherItems.add(WeatherItem(title,
            today_ic, today_weather, today_temp.toInt(), today_humidity,
            tomorrow_ic, tomorrow_weather, tomorrow_temp.toInt(), tomorrow_humidity))
    }

    fun clear(){
        weatherItems.clear()
    }


}