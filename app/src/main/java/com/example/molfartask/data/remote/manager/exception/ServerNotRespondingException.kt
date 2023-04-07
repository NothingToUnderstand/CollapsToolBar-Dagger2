package com.example.molfartask.data.remote.manager.exception

import com.example.molfartask.utils.SERVER_NOT_RESPONDING
import java.io.IOException

class ServerNotRespondingException : IOException() {
    override val message: String
        get() = SERVER_NOT_RESPONDING
}