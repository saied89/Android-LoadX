package com.saied.home.loadingX

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.preference.PreferenceManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.saied.home.androidloadingexts.loadX
import com.saied.home.loadingX.setting.SettingsActivity
import com.saied.home.loadingexts.R

class MainActivity : AppCompatActivity() {

    val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }
    private var mShowingLayout = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun show(v: View) {
        val tag = v.tag as String
        val id = resources.getIdentifier(tag, "layout", packageName)
        setContentView(id)
        mShowingLayout = true
    }

//    fun load(v: View){
//        val hideTarget = sharedPreferences.getBoolean(getString(R.string.hideTarget), false)
//        val progressBarSize = dpToPixel(sharedPreferences.getInt(getString(R.string.progressSize), 0), resources.displayMetrics)
//        val progressColor = sharedPreferences.getInt(getString(R.string.progressColor), 0)
//        val progressBgColor = sharedPreferences.getInt(getString(R.string.progressBGColor), 0)
//        v.loadX(hideTarget = hideTarget, progressbarSize = progressBarSize, progressbarColor = progressColor, backgroundColor = progressBgColor).apply {
//            this?.setOnClickListener {
//                v.loadX()
//            }
//        }
//    }

    fun load(v: View){
        v.isEnabled = false
        val loadView = v.loadX()
        loadView?.setOnClickListener {
            v.isEnabled = true
            v.loadX()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.setttings -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (mShowingLayout) {
            setContentView(R.layout.activity_main)
            mShowingLayout = false
        } else {
            super.onBackPressed()
        }
    }

    fun dpToPixel(dp: Int, metrics: DisplayMetrics?) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), metrics).toInt()


}
