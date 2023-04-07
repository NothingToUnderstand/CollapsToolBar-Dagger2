package com.example.molfartask.data.remote.repository

import com.example.molfartask.data.remote.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : RemoteRepository {
    override suspend fun getSubliminal() = remoteDataSource.getSubliminal()
}