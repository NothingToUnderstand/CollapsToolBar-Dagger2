package com.example.molfartask.data.remote.manager.exception

import com.example.molfartask.utils.NO_INTERNET
import java.io.IOException

class NetworkConnectionException : IOException() {
    override val message: String
        get() = NO_INTERNET
}