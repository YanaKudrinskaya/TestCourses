package com.yanakudrinskaya.testcourses.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(): ViewModel() {
    private val navigationEvents = MutableLiveData<Boolean>(true)
    fun getNavigationEvents(): LiveData<Boolean> = navigationEvents

    fun setNavigationVisible(visible: Boolean) {
        navigationEvents.value = visible
    }
}