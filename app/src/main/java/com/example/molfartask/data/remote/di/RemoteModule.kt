package com.example.molfartask.data.remote.di

import com.example.molfartask.BuildConfig
import com.example.molfartask.data.remote.datasource.RemoteDataSource
import com.example.molfartask.data.remote.datasource.RemoteDataSourceImpl
import com.example.molfartask.data.remote.interceptor.HeaderInterceptor
import com.example.molfartask.data.remote.interceptor.NetWorkExceptionInterceptor
import com.example.molfartask.data.remote.interceptor.NotSuccessfulResponseInterceptor
import com.example.molfartask.data.remote.repository.RemoteRepository
import com.example.molfartask.data.remote.repository.RemoteRepositoryImpl
import com.example.molfartask.data.remote.service.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    companion object {
        @Provides
        fun getHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()
        @Provides
        fun getNotSuccessfulResponseInterceptor(): NotSuccessfulResponseInterceptor = NotSuccessfulResponseInterceptor()

        @Provides
        @Singleton
        fun getOkHttpClient(
            headerInterceptor: HeaderInterceptor,
            netWorkExceptionInterceptor: NetWorkExceptionInterceptor,
            notSuccessfulResponseInterceptor: NotSuccessfulResponseInterceptor
        ): OkHttpClient {
            return OkHttpClient
                .Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(netWorkExceptionInterceptor)
                .addInterceptor(notSuccessfulResponseInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRemoteDataSource(client: OkHttpClient): RemoteService {
            return Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RemoteService::class.java)
        }
    }


}