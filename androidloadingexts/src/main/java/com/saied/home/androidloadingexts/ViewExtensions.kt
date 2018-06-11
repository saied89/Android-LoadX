package com.saied.home.androidloadingexts

import android.os.Build
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import me.zhanghai.android.materialprogressbar.MaterialProgressBar

//fun View.loading(showLoading: Boolean){
//    val container: ViewGroup =
//            if(parent is ViewGroup)
//                (parent as ViewGroup)
//            else
//                throw Exception("Doesn't work for View Hierarchies Root. Consider wrapping The view in a ViewGroup")
//    //val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false)
//    val loadingView = MaterialProgressBar(context).apply {
//        layoutParams = this@loading.layoutParams
//    }
//    if(showLoading){
//        //val container = if(this is ViewGroup) this else (parent as ViewGroup)
//        this.visibility = View.GONE
//        //val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false)
//        container.addView(loadingView)
//    }else{
//        (1 until (container.childCount)).map {
//            container.getChildAt(it)
//        }.first {
//            it is MaterialProgressBar
//        }.apply {
//            container.removeView(this)
//        }
//        visibility = View.VISIBLE
//    }
//}

fun View.loadingV2(showLoading: Boolean){
    if(parent !is ViewGroup)
        throw IllegalStateException("The parent of loading target is not a ViewGroup. Is it the rootView?. Consider wrapping the target in a FrameLayout.")
    else if(parent is LinearLayout)
        loadingInvolved(showLoading)
    else
        loadingSimple(showLoading)
}

private fun View.loadingSimple(showLoading: Boolean){
    val container = parent as ViewGroup
    if(showLoading){
        val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false).apply {
            layoutParams = this@loadingSimple.layoutParams
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                elevation = this@loadingSimple.elevation
            }
        }
        container.addView(loadingView)
    }else{
        container.removeView(container.findViewById(R.id.progressbarFrame))
    }
}

private fun View.loadingInvolved(showLoading: Boolean){
    val container = parent as LinearLayout
    if(showLoading){
        val loadingLayoutParams = (layoutParams as LinearLayout.LayoutParams)
//                .apply {
//            setMargins(leftMargin, -(this@loadingInvolved.height), rightMargin, bottomMargin)
//        }
        loadingLayoutParams.apply {
            setMargins(leftMargin, -(this@loadingInvolved.height), rightMargin, bottomMargin)
        }
        val loadingView = LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                elevation = this@loadingInvolved.elevation
            }
        }
        container.addView(loadingView, loadingLayoutParams)
    }else{
        container.removeView(container.findViewById(R.id.progressbarFrame))
    }
}
