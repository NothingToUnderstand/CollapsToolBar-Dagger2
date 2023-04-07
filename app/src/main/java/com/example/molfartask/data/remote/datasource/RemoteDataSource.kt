package com.example.molfartask.data.remote.datasource

import com.example.molfartask.data.entity.Subliminal
import retrofit2.Response


interface RemoteDataSource {
   suspend fun getSubliminal(): Response<Subliminal>
}