package com.yanakudrinskaya.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val navigationEvents = MutableLiveData<Boolean>(true)
    fun getNavigationEvents(): LiveData<Boolean> = navigationEvents

    fun setNavigationVisible(visible: Boolean) {
        navigationEvents.value = visible
    }
}