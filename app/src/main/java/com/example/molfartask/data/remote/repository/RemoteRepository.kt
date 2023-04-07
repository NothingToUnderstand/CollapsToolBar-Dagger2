package com.example.molfartask.data.remote.repository

import com.example.molfartask.data.entity.Subliminal
import retrofit2.Response

interface RemoteRepository {
   suspend fun getSubliminal(): Response<Subliminal>
}