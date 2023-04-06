package com.example.molfartask.di

import com.example.molfartask.presentation.MainActivity
import com.example.molfartask.presentation.di.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        MainActivityComponent::class
    ]
)
abstract class ActivityBindsModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindsMainActivityInjectorFactory(factory: MainActivityComponent.Factory): AndroidInjector.Factory<*>
}