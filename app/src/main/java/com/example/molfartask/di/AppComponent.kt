package com.example.molfartask.di

import com.example.molfartask.App
import com.example.molfartask.di.viewmodel.ViewModelModule
import com.example.molfartask.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityBindsModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: App): Builder
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }
}