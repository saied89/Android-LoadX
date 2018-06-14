package com.saied.home.loadingexts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.saied.home.androidloadingexts.loading
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        loadButtonRelative.setOnClickListener {
            it.loading(true)
        }
        relativeLayout.setOnClickListener {
            loadButtonRelative.loading(false)
        }
        loadButtonLinear.setOnClickListener {
            it.loading(true)
        }
        linearLayoutVertical.setOnClickListener {
            loadButtonLinear.loading(false)
        }
        loadButtonLinearHorizontal.setOnClickListener {
            it.loading(true)
        }
        linearLayoutHorizonal.setOnClickListener {
            loadButtonLinearHorizontal.loading(false)
        }

//        loadButtonLinearNegMargin.setOnClickListener {
//         it.loading(true)
//        }

//        loadButtonConstraint.setOnClickListener {
//            it.loadingV2(true)
//        }
//        constraintLayout.setOnClickListener {
//            loadButtonConstraint.loadingV2(false)
//        }
    }
}
