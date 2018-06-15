package com.saied.home.androidloadingexts

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import me.zhanghai.android.materialprogressbar.MaterialProgressBar

const val CONSTANT_ID_OFFSET = 376

fun View.loading(showLoading: Boolean,
                   progressbarSize: Int = 40,
                   backgroundColor: Int = Color.TRANSPARENT,
                   progressbarColor: ColorStateList? = null,
                   loadingView: View = generateLoadingView(progressbarSize = progressbarSize, backgroundColor = backgroundColor, progressTintList = progressbarColor)
): View{
    if(parent !is ViewGroup)
        throw IllegalStateException("The parent of loading target is not a ViewGroup. Is it the rootView?. Consider wrapping the target in a FrameLayout.")
    else if(parent is LinearLayout)
        loadingLinear(showLoading, loadingView)
    else if(parent is ConstraintLayout)
        loadingConstraint(showLoading)
    else
        loadingSimple(showLoading, loadingView)
    return loadingView
}

private fun View.generateLoadingView(progressbarSize: Int, backgroundColor: Int, progressTintList: ColorStateList?): View =
        FrameLayout(context).apply {
            addView(View(context).apply {
                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                setBackgroundColor(backgroundColor)
            })
            addView(MaterialProgressBar(context).apply {
                layoutParams = FrameLayout.LayoutParams(dpToPixel(progressbarSize, context),
                        dpToPixel(progressbarSize, context)).apply {
                    gravity = Gravity.CENTER
                }
                if(progressTintList != null)
                    this.progressTintList = progressTintList
            }
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                elevation = this@generateLoadingView.elevation
            }
            id = this@generateLoadingView.id - CONSTANT_ID_OFFSET
            isClickable = true
            isFocusable = true
        }

private fun View.loadingSimple(showLoading: Boolean, loadingView: View){
    val container = parent as ViewGroup
    if(showLoading){
        container.addView(loadingView, container.indexOfChild(this)+1, layoutParams)
    }else{
        container.removeView(container.findViewById(id - CONSTANT_ID_OFFSET))
    }
}

private fun View.loadingConstraint(showLoading: Boolean){
    throw IllegalStateException("Currently not supporting ConstraintLayout")
}

private fun View.loadingLinear(showLoading: Boolean, loadingView: View){
    val container = parent as LinearLayout
    if(showLoading){
        val loadingLayoutParams = LinearLayout.LayoutParams(layoutParams as LinearLayout.LayoutParams)
        loadingLayoutParams.apply {
            if(container.orientation == LinearLayout.VERTICAL)
                topMargin = -(this@loadingLinear.height + bottomMargin)
            else
                marginStart = -(this@loadingLinear.width + marginEnd)
        }
        container.addView(loadingView, container.indexOfChild(this)+1,loadingLayoutParams)
    }else{
        container.removeView(container.findViewById(id - CONSTANT_ID_OFFSET))
    }
}

fun dpToPixel(dp: Int, context: Context): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()

