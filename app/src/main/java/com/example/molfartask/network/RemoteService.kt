package com.example.molfartask.network

import com.example.molfartask.entity.Subliminals
import retrofit2.Response
import retrofit2.http.GET


interface RemoteService {
    @GET("subliminals")
    suspend fun getSubliminals(): Response<Subliminals>
}