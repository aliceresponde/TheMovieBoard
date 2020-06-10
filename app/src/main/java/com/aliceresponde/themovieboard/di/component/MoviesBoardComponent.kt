package com.aliceresponde.themovieboard.di.component

import android.app.Application
import com.aliceresponde.themovieboard.MovieApp
import com.aliceresponde.themovieboard.di.module.data.DataModule
import com.aliceresponde.themovieboard.di.module.ui.AppModule
import com.aliceresponde.themovieboard.di.module.ui.ViewModelFactoryModule
import com.aliceresponde.themovieboard.di.module.ui.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        ViewModelsModule::class,
        ViewModelFactoryModule::class
    ]
)
interface MoviesBoardComponent {

//    val moviesViewModel: MoviesViewModel
//    val seriesViewModel: SeriesViewModel
//    val detailViewModel: DetailViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MoviesBoardComponent
    }

    fun inject(app: MovieApp)


    //    @Component.Factory method is missing parameters for required modules or components
    //fun appComponent(appComponent: AppComponent): Builder

}