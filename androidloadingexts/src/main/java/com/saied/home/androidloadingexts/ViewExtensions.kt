package com.saied.home.androidloadingexts

import android.os.Build
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.zhanghai.android.materialprogressbar.MaterialProgressBar

fun View.loading(showLoading: Boolean){
    val container: ViewGroup =
            if(parent is ViewGroup)
                (parent as ViewGroup)
            else
                throw Exception("Doesn't work for View Hierarchies Root. Consider wrapping The view in a ViewGroup")
    //val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false)
    val loadingView = MaterialProgressBar(context).apply {
        layoutParams = this@loading.layoutParams
    }
    if(showLoading){
        //val container = if(this is ViewGroup) this else (parent as ViewGroup)
        this.visibility = View.GONE
        //val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false)
        container.addView(loadingView)
    }else{
        (1 until (container.childCount)).map {
            container.getChildAt(it)
        }.first {
            it is MaterialProgressBar
        }.apply {
            container.removeView(this)
        }
        visibility = View.VISIBLE
    }
}

fun View.loadingV2(showLoading: Boolean){
    val container: ViewGroup =
            if(parent is ViewGroup)
                (parent as ViewGroup)
            else
                throw Exception("Doesn't work for View Hierarchies Root. Consider wrapping The view in a ViewGroup")
    val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false).apply {
        layoutParams = this@loadingV2.layoutParams
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = this@loadingV2.elevation
        }
    }
    if(showLoading){
        container.addView(loadingView)
        //this.visibility = View.INVISIBLE
    }else{
        container.removeView(container.findViewById(R.id.progressbarFrame))
    }
}
