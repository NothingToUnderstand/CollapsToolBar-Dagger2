package com.example.molfartask.data.remote.manager.exception

import com.example.molfartask.R
import com.example.molfartask.utils.ResourceManager
import java.io.IOException

class NetworkConnectionException(
    private val resourceManager: ResourceManager
) : IOException() {
    override val message: String
        get() = resourceManager.getString(R.string.no_internet_connection)
}