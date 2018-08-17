package com.saied.home.androidloadingexts

import android.graphics.Color
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import me.zhanghai.android.materialprogressbar.MaterialProgressBar

const val CONSTANT_ID_OFFSET = 376

fun View.loadX(showLoading: Boolean = !hasLoading(),
               hideTarget: Boolean = false,
               progressbarSize: Int? = null,
               backgroundColor: Int = Color.TRANSPARENT,
               progressbarColor: Int? = null,
               loadingView: View = generateLoadingView(progressbarSize = progressbarSize, backgroundColor = backgroundColor, progressColor = progressbarColor)
): View{
    if(parent !is ViewGroup)
        throw IllegalStateException("The parent of loadX target is not a ViewGroup. Is it the rootView?. Consider wrapping the target in a FrameLayout.")
    else if(parent is LinearLayout)
        loadingLinear(showLoading, loadingView, hideTarget)
    else if(parent is ConstraintLayout)
        loadingConstraint(showLoading, loadingView, hideTarget)
    else
        loadingSimple(showLoading, loadingView, hideTarget)
    return loadingView
}

fun View.hasLoading(): Boolean{
    val container = parent as ViewGroup
    return container.findViewById<View>(id - CONSTANT_ID_OFFSET) != null
}

private fun View.generateLoadingView(progressbarSize: Int?, backgroundColor: Int, progressColor: Int?): View {
    val materialProgressBar = MaterialProgressBar(context).apply {
        val progressBarDP = if(progressbarSize != null) dpToPixel(progressbarSize ,context) else minOf(this@generateLoadingView.measuredHeight, this@generateLoadingView.measuredWidth)
        layoutParams = FrameLayout.LayoutParams(progressBarDP,
                progressBarDP).apply {
            gravity = Gravity.CENTER
        }
        if (progressColor != null)
            this.indeterminateDrawable.setColorFilter(progressColor, android.graphics.PorterDuff.Mode.SRC_IN)
    }
    return FrameLayout(context).apply {
        addView(View(context).apply {
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            setBackgroundColor(backgroundColor)
        })
        addView(materialProgressBar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = this@generateLoadingView.elevation + 1
        }
        id = this@generateLoadingView.id - CONSTANT_ID_OFFSET
        isClickable = true
        isFocusable = true
    }
}

private fun View.loadingSimple(showLoading: Boolean, loadingView: View, invisibleTarget: Boolean){
    val container = parent as ViewGroup
    if(showLoading){
        container.addView(loadingView, container.indexOfChild(this)+1, layoutParams)
    }else{
        container.removeView(container.findViewById(id - CONSTANT_ID_OFFSET))
    }
    visibility = if(invisibleTarget) View.INVISIBLE else View.VISIBLE
}

private fun View.loadingConstraint(showLoading: Boolean, loadingView: View, invisibleTarget: Boolean){
    val container = parent as ConstraintLayout
    if(showLoading){
        val constraintSet = ConstraintSet().apply {
            clone(container)
        }
        val params = ViewGroup.LayoutParams(layoutParams)
        container.addView(loadingView,params)
        constraintSet.connect(loadingView.id, ConstraintSet.START, this.id, ConstraintSet.START)
        constraintSet.connect(loadingView.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP)
        constraintSet.connect(loadingView.id, ConstraintSet.END, this.id, ConstraintSet.END)
        constraintSet.connect(loadingView.id, ConstraintSet.BOTTOM, this.id, ConstraintSet.BOTTOM)
        constraintSet.applyTo(container)
    }else{
        container.removeView(container.findViewById(this.id - CONSTANT_ID_OFFSET))
    }
    visibility = if(invisibleTarget) View.INVISIBLE else View.VISIBLE
}

private fun View.loadingLinear(showLoading: Boolean, loadingView: View, invisibleTarget: Boolean){
    val container = parent as LinearLayout
    if(showLoading){
        val loadingLayoutParams = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams(layoutParams as LinearLayout.LayoutParams)
        } else {
            duplicateLinearParams(layoutParams as LinearLayout.LayoutParams)
        }
        loadingLayoutParams.apply {
            if(container.orientation == LinearLayout.VERTICAL)
                topMargin = -(this@loadingLinear.height + bottomMargin)
            else
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    marginStart = -(this@loadingLinear.width + marginEnd)
                }
            height = this@loadingLinear.measuredHeight
            width =  this@loadingLinear.measuredWidth
        }
        container.addView(loadingView, container.indexOfChild(this)+1, loadingLayoutParams)
    }else{
        container.removeView(container.findViewById(id - CONSTANT_ID_OFFSET))
    }
    visibility = if(invisibleTarget) View.INVISIBLE else View.VISIBLE
}


