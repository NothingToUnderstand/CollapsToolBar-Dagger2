package com.example.molfartask

import com.example.molfartask.di.AppModule
import com.example.molfartask.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .app(this)
            .appModule(AppModule(this))
            .build()

}