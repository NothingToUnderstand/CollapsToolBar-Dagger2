package com.example.molfartask.data.remote.repository

import com.example.molfartask.data.entity.Subliminal


interface RemoteRepository {
   suspend fun getSubliminal(): Subliminal
}