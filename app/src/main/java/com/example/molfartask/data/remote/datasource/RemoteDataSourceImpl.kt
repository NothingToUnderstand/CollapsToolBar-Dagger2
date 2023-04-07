package com.example.molfartask.data.remote.datasource

import com.example.molfartask.data.remote.service.RemoteService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteDataSource {
    override suspend fun getSubliminal() = remoteService.getSubliminal()
}