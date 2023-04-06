package com.example.molfartask.presentation.di

import com.example.molfartask.presentation.MainActivity
import com.example.molfartask.di.scope.PerActivity
import com.example.molfartask.presentation.subliminal.di.SubliminalFragmentComponent
import com.example.molfartask.presentation.subliminal.fragment.SubliminalFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@PerActivity
@Subcomponent(
    modules = [
        MainActivityComponent.FragmentBindsModule::class
    ]
)
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MainActivity>

    @Module(
        subcomponents = [
            SubliminalFragmentComponent::class
        ]
    )
    interface FragmentBindsModule {

        @Binds
        @IntoMap
        @ClassKey(value = SubliminalFragment::class)
        fun bindSubliminalFragmentComponentFactory(factory: SubliminalFragmentComponent.Factory): AndroidInjector.Factory<*>
    }
}