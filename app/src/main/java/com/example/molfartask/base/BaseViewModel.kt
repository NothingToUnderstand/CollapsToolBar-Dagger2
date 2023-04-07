package com.example.molfartask.base

import androidx.lifecycle.ViewModel
import com.example.molfartask.utils.ErrorHandler


abstract class BaseViewModel(
    private val errorHandler: ErrorHandler
) : ViewModel() {

    fun observeNotifierLiveData() = errorHandler.getNotifierLiveData()
    fun sendNotifier(t: Throwable) = errorHandler.sendNotifier(t)
}

