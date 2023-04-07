package com.example.molfartask.domain.usecase

import com.example.molfartask.R
import com.example.molfartask.data.entity.Record
import com.example.molfartask.data.remote.repository.RemoteRepository
import com.example.molfartask.domain.Result
import com.example.molfartask.utils.ResourceManager
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val recourseManager: ResourceManager
) {
    suspend fun getRecords(): Result<List<Record>> =
        remoteRepository.getSubliminal().record?.let {
            Result.Success(it)
        } ?: Result.NoData(recourseManager.getString(R.string.no_data))
}
