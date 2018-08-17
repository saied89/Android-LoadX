package com.saied.home.androidloadingexts

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.widget.LinearLayout

fun dpToPixel(dp: Int, context: Context): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()

fun duplicateLinearParams(source: LinearLayout.LayoutParams): LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(source.width, source.height).apply {
            this.width = source.width
            this.height = source.height

            this.leftMargin = source.leftMargin
            this.topMargin = source.topMargin
            this.rightMargin = source.rightMargin
            this.bottomMargin = source.bottomMargin

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                this.marginStart = source.marginStart
                this.marginEnd = source.marginEnd
            }

//            this.mMarginFlags = source.mMarginFlags
        }
