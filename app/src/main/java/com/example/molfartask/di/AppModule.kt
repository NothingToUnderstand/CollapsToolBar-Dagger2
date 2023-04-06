package com.example.molfartask.di

import android.app.Application
import android.content.Context
import com.example.molfartask.di.scope.AppQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    @AppQualifier
    fun provideApplication() = application

    @Provides
    @Singleton
    @AppQualifier
    fun provideContext(): Context = application.applicationContext

}