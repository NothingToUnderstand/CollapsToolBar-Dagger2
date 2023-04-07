package com.example.molfartask.data.remote.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NetworkManager @Inject constructor(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var connectionState = MutableStateFlow(State.UNKNOWN)

    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val networkCallBack = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                connectionState.value = State.AVAILABLE
            }

            override fun onUnavailable() {
                connectionState.value = State.UNAVAILABLE
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallBack)
    }

    fun checkConnection(): Boolean = runBlocking {
        connectionState.value == State.AVAILABLE
    }

    fun observeConnection() = connectionState.map { it == State.AVAILABLE }
}

enum class State {
    AVAILABLE,
    UNAVAILABLE,
    UNKNOWN
}