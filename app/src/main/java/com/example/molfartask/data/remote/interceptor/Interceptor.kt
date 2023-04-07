package com.example.molfartask.data.remote.interceptor

import com.example.molfartask.BuildConfig
import com.example.molfartask.data.remote.manager.NetworkManager
import com.example.molfartask.data.remote.manager.exception.NetworkConnectionException
import com.example.molfartask.data.remote.manager.exception.NotSuccessfulResponseException
import com.example.molfartask.data.remote.manager.exception.ServerNotRespondingException
import com.example.molfartask.utils.ResourceManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        chain.request().newBuilder()
            .apply {
                addHeader("Authorization", BuildConfig.API_KEY)
            }.build()
            .run { chain.proceed(this) }
}

class NetWorkExceptionInterceptor @Inject constructor(
    private val networkManager: NetworkManager,
    private val resourceManager: ResourceManager
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (exc: UnknownHostException) {
            throw if (networkManager.checkConnection()) {
                ServerNotRespondingException(resourceManager)
            } else {
                NetworkConnectionException(resourceManager)
            }
        } catch (exc: IOException) {
            throw exc
        }

    }
}

class NotSuccessfulResponseInterceptor @Inject constructor(
    private val resourceManager: ResourceManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).also {
            if (!it.isSuccessful) {
                throw NotSuccessfulResponseException(resourceManager)
            }
        }
    }

}

