package com.aliceresponde.themovieboard.di.module.ui

import androidx.lifecycle.ViewModelProvider
import com.aliceresponde.themovieboard.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}