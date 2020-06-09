package com.aliceresponde.themovieboard

import android.app.Application
import com.aliceresponde.themovieboard.di.component.MoviesBoardComponent

class MovieApp : Application() {
    private lateinit var component: MoviesBoardComponent
        private set

    override fun onCreate() {
        super.onCreate()

//        component = DaggerMoviesBoardComponent.factory().create(this)
    }
}