package com.example.molfartask.domain.usecase

import com.example.molfartask.data.entity.Record
import com.example.molfartask.data.remote.manager.exception.NetworkConnectionException
import com.example.molfartask.data.remote.manager.exception.ServerNotRespondingException
import com.example.molfartask.data.remote.repository.RemoteRepository
import com.example.molfartask.domain.Result
import com.example.molfartask.utils.NO_DATA
import com.example.molfartask.utils.RESPONSE_IS_NOT_SUCCESSFUL
import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    suspend fun getRecords(): Result<List<Record>> {
        return try {
            val response = remoteRepository.getSubliminal()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it.record)
                } ?: Result.NoData(NO_DATA)
            } else {
                Result.Error(RESPONSE_IS_NOT_SUCCESSFUL)
            }
        } catch (exc: IOException) {
            when (exc) {
                is NetworkConnectionException,
                is ServerNotRespondingException -> {
                    Result.NoInternet(exc.message)
                }
                else -> Result.Error(exc.message)
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                Result.Error(t.cause?.message)
            } else {
                throw t
            }
        }
    }

}