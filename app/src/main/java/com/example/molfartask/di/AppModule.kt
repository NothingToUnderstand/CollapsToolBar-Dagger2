package com.example.molfartask.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

}