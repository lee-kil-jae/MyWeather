package com.afterwork.myweather.view.base

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(_visible: Boolean) {
    Log.d("ViewExtention", "ViewExtention - refreshing : $_visible")
    isRefreshing = _visible
}

@BindingAdapter("setVisibility")
fun setVisibility(_view: View, _visible: Boolean) {
    Log.d("ViewExtention", "ViewExtention - setVisibility : $_visible")
    _view.visibility = if(_visible) View.VISIBLE else View.GONE
}

@BindingAdapter("setTemp")
fun setTemp(_view: TextView, temp: Double) {
    Log.d("ViewExtention", "ViewExtention - setTemp : $temp")
    _view.text = "${temp.toInt()}°C"
}

@BindingAdapter("setHumidity")
fun setHumidity(_view: TextView, humidity: Int) {
    Log.d("ViewExtention", "ViewExtention - setHumidity : $humidity")
    _view.text = "${humidity}%"
}
