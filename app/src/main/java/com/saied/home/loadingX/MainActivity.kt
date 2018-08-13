package com.saied.home.loadingX

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.saied.home.androidloadingexts.loadX
import com.saied.home.loadingX.setting.SettingsActivity
import com.saied.home.loadingexts.R

class MainActivity : AppCompatActivity() {

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

    fun load(v: View){
        v.loadX(hideTarget = true).apply {
            setOnClickListener {
                v.loadX(hideTarget = false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

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


}
