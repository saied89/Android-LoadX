package com.saied.home.loadingX

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.support.v4.content.res.ResourcesCompat
import com.saied.home.loadingexts.R

class MainViewModel(application: Application): AndroidViewModel(application) {
    val loadingParamsViewModel = MutableLiveData<LoadingParams>()

    init {
        loadingParamsViewModel.value = LoadingParams(color = ResourcesCompat.getColor(application.resources, R.color.colorAccent, null))
    }
}

data class LoadingParams(val size: Int = 40, val bgOpacity: Int = 1, val color: Int)