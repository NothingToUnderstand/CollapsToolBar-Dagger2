package com.example.molfartask.network

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteDataSource {
    override suspend fun getSubliminals() = remoteService.getSubliminals()
}