package com.example.molfartask.utils

import android.content.Context
import javax.inject.Inject

class ResourceManager @Inject constructor(
    private val context: Context
) {
    fun getString(id: Int) = context.getString(id)
}