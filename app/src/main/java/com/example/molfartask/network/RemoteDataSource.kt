package com.example.molfartask.network

import com.example.molfartask.entity.Subliminals
import retrofit2.Response


interface RemoteDataSource {
   suspend fun getSubliminals(): Response<Subliminals>
}