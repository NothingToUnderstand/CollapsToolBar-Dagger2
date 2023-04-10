package com.example.molfartask.domain.usecase

import com.example.molfartask.data.remote.repository.RemoteRepository
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    suspend fun getSubliminal() = remoteRepository.getSubliminal()
}
