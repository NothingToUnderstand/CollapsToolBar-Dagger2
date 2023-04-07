package com.example.molfartask.data.remote.repository

import com.example.molfartask.data.entity.Subliminal
import com.example.molfartask.data.remote.datasource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {
    override suspend fun getSubliminal(): Response<Subliminal> = remoteDataSource.getSubliminal()
}