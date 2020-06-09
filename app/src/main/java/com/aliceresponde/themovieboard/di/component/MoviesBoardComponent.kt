package com.aliceresponde.themovieboard.di.component

import android.app.Application
import com.aliceresponde.themovieboard.di.module.DataModule
import com.aliceresponde.themovieboard.di.module.AppModule
import com.aliceresponde.themovieboard.di.module.ui.ViewModelsModule
import com.aliceresponde.themovieboard.ui.detail.DetailViewModel
import com.aliceresponde.themovieboard.ui.main.movie.MoviesViewModel
import com.aliceresponde.themovieboard.ui.main.serie.SeriesViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        AppModule::class,
        ViewModelsModule::class
    ]
)
interface MoviesBoardComponent {

    val moviesViewModel: MoviesViewModel
    val seriesViewModel: SeriesViewModel
    val detailViewModel: DetailViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MoviesBoardComponent
    }
}