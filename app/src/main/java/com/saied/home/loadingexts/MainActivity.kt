package com.saied.home.loadingexts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.saied.home.androidloadingexts.loadX

class MainActivity : AppCompatActivity() {

    private var mShowingLayout = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun show(v: View) {
        val tag = v.tag as String
        val id = resources.getIdentifier(tag, "layout", packageName)
        setContentView(id)
        mShowingLayout = true
    }

    fun load(v: View){
        v.loadX().apply {
            setOnClickListener {
                v.loadX()
            }
        }
    }

    override fun onBackPressed() {
        if (mShowingLayout) {
            setContentView(R.layout.activity_main2)
            mShowingLayout = false
        } else {
            super.onBackPressed()
        }
    }


}
