package com.example.molfartask.data.remote.manager.exception

import java.io.IOException

class ServerNotRespondingException(
    private val errorName: String
) : IOException() {
    override val message: String
        get() = errorName
}