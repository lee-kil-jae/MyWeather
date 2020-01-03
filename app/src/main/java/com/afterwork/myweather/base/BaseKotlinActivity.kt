package com.afterwork.myweather.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * BaseKotlinActivity<ActivitySbsMainBinding>와 같이 상속 받을 때 ActivitySbsMainBinding과
 * 같은 파일이 자동 생성되지 않는다면
 * 1. 해당 액티비티 레이아웃이 <layout></layout>로 감싸져 있는지 확인
 * 2. 다시 빌드 수행 or 클린 빌드 후 다시 빌드 수행
 * 3. 이름 확인 sbs_main_activity -> ActivitySbsMainBinding
 */

abstract class BaseKotlinActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewDataBinding: T

    /**
     * setContentView를 호출할 layout 리소스 ID.
     * Ex). R.layout.activity_sbs_main
     */
    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }

}