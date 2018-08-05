package com.saied.home.loadingexts.setting

import android.os.Bundle
import android.preference.PreferenceFragment
import com.saied.home.loadingexts.R

class SettingsFragment: PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }
}