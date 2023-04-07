package com.example.molfartask.data.remote.service

import com.example.molfartask.data.entity.Subliminal
import retrofit2.Response
import retrofit2.http.GET


interface RemoteService {
    @GET("subliminals")
    suspend fun getSubliminal(): Response<Subliminal>
}