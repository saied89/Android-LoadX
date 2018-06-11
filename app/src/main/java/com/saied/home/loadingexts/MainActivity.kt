package com.saied.home.loadingexts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.saied.home.androidloadingexts.loadingV2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadButtonRelative.setOnClickListener {
            it.loadingV2(true)
        }
        loadButtonLinear.setOnClickListener {
            it.loadingV2(true)
        }
    }
}
