package com.example.molfartask.utils

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val resourceManager: ResourceManager) {
    private val notifierLiveData = MutableLiveData<String>()

    fun getNotifierLiveData() = notifierLiveData
    fun sendNotifier(t: Throwable) {
        notifierLiveData.postValue(t.message)
    }
}