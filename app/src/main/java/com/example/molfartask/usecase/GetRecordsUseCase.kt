package com.example.molfartask.usecase

import com.example.molfartask.entity.Record
import com.example.molfartask.network.RemoteDataSource
import com.example.molfartask.presentation.Result
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getRecords(): Result<List<Record>> {
        return try {
            val response = remoteDataSource.getSubliminals()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it.records)
                } ?: Result.Error("No data")
            } else {
                Result.Error("response is not successful")
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                Result.Error("${t.cause}")
            } else {
                throw t
            }
        }
    }

}