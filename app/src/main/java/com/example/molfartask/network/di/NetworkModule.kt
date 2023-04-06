package com.example.molfartask.network.di

import com.example.molfartask.BuildConfig
import com.example.molfartask.di.scope.PerFragment
import com.example.molfartask.network.RemoteDataSource
import com.example.molfartask.network.RemoteDataSourceImpl
import com.example.molfartask.network.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
abstract class NetworkModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", BuildConfig.API_KEY)
                        .build()
                    chain.proceed(newRequest)
                }
                .build()
        }

        @Provides
        @Singleton
        fun provideRemoteDataSource(client: OkHttpClient): RemoteService {
            return Retrofit
                .Builder()
                .baseUrl("https://api.airtable.com/v0/app9rx3VcDOx92Bmh/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RemoteService::class.java)
        }
    }


}