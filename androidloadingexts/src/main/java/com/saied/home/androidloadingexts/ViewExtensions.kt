package com.saied.home.androidloadingexts

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

fun View.loadingV2(showLoading: Boolean){
    if(parent !is ViewGroup)
        throw IllegalStateException("The parent of loading target is not a ViewGroup. Is it the rootView?. Consider wrapping the target in a FrameLayout.")
    else if(parent is LinearLayout)
        loadingLinear(showLoading)
    else
        loadingSimple(showLoading)
}
const val CONSTANT_ID_OFFSET = 376
private fun View.loadingSimple(showLoading: Boolean){
    val container = parent as ViewGroup
    if(showLoading){
        container.addView(generateLoadingView(), container.indexOfChild(this)+1, layoutParams)
    }else{
        container.removeView(container.findViewById(id - CONSTANT_ID_OFFSET))
    }
}

private fun View.generateLoadingView(): View {
    return LayoutInflater.from(context).inflate(R.layout.layout_progressbar, parent as ViewGroup, false).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = this@generateLoadingView.elevation
        }
        id = this@generateLoadingView.id - CONSTANT_ID_OFFSET
    }
}

private fun View.loadingLinear(showLoading: Boolean){
    val container = parent as LinearLayout
    if(showLoading){
        val loadingLayoutParams = LinearLayout.LayoutParams(layoutParams as LinearLayout.LayoutParams)
        loadingLayoutParams.apply {
            if(container.orientation == LinearLayout.VERTICAL)
                topMargin = -(this@loadingLinear.height + topMargin)
            else
                marginStart = -(this@loadingLinear.width + marginStart)
        }
        container.addView(generateLoadingView(), container.indexOfChild(this)+1,loadingLayoutParams)
    }else{
        container.removeView(container.findViewById(id - CONSTANT_ID_OFFSET))
    }
}

