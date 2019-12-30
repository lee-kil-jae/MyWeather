package com.afterwork.myweather.view

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.afterwork.myweather.MainWeatherRecyclerViewAdapter
import com.afterwork.myweather.base.BaseKotlinActivity
import com.afterwork.myweather.viewmodel.MainViewModel
import com.afterwork.myweather.R
import com.afterwork.myweather.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_header.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {

    private val TAG = "MainActivity"

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    private val mainWeatherRecyclerViewAdapter: MainWeatherRecyclerViewAdapter by inject()

    override fun initStartView() {
        Log.d(TAG, "initStartView()")
        rv_main_list.run{
            adapter = mainWeatherRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(1, 1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        Log.d(TAG, "initDataBinding()")
        viewModel.locationWeatherResponseLiveData.observe(this, Observer {
            Log.d(TAG, "initDataBinding: ${it.title}")

            if(pb_loading.visibility == View.VISIBLE)
                pb_loading.visibility = View.GONE

            val today = it.consolidated_weather[0]
            val tomorrow = it.consolidated_weather[1]
            mainWeatherRecyclerViewAdapter.addWeatherItem(it.title,
                today.weather_state_abbr, today.weather_state_name, today.the_temp, today.humidity,
                tomorrow.weather_state_abbr, tomorrow.weather_state_name, tomorrow.the_temp, tomorrow.humidity)

            mainWeatherRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        Log.d(TAG, "initAfterBinding()")
        bt_search.setOnClickListener {
            Log.d(TAG, "search Keyword: ${et_search.text.toString().trim()}")
            mainWeatherRecyclerViewAdapter.clear()
            mainWeatherRecyclerViewAdapter.notifyDataSetChanged()
            pb_loading.visibility = View.VISIBLE
            viewModel.getLocationList(et_search.text.toString().trim())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainWeatherRecyclerViewAdapter.clear()
    }
}
