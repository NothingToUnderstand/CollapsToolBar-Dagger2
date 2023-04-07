package com.example.molfartask.data.remote.datasource

import com.example.molfartask.data.entity.Subliminal


interface RemoteDataSource {
   suspend fun getSubliminal(): Subliminal
}