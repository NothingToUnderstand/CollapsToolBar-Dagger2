package com.example.molfartask.presentation.subliminal.di

import androidx.lifecycle.ViewModel
import com.example.molfartask.di.viewmodel.DaggerViewModelKey
import com.example.molfartask.di.scope.PerFragment
import com.example.molfartask.presentation.subliminal.fragment.SubliminalFragment
import com.example.molfartask.presentation.subliminal.fragment.SubliminalViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        SubliminalFragmentComponent.BindsModule::class
    ]
)

interface SubliminalFragmentComponent : AndroidInjector<SubliminalFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<SubliminalFragment>

    @Module
    interface BindsModule {

        @Binds
        @IntoMap
        @DaggerViewModelKey(SubliminalViewModel::class)
        fun bindsSubliminalViewModel(viewModel: SubliminalViewModel): ViewModel
    }
}