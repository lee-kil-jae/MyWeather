package com.afterwork.myweather.view.base

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(_visible: Boolean) {
    Log.d("MainViewExtention", "MainViewExtention - refreshing : $_visible")
    isRefreshing = _visible
}

@BindingAdapter("setVisibility")
fun setVisibility(_view: View, _visible: Boolean) {
    Log.d("MainViewExtention", "MainViewExtention - setVisibility : $_visible")
    _view.visibility = if(_visible) View.VISIBLE else View.GONE
}
