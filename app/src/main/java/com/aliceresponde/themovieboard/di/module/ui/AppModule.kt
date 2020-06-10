package com.aliceresponde.themovieboard.di.module.ui

import com.aliceresponde.themovieboard.MovieApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MovieApp) {
    @Provides
    @Singleton
    fun providesContext() = app
}