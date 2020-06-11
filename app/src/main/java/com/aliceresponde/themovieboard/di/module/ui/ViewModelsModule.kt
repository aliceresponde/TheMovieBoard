package com.aliceresponde.themovieboard.di.module.ui

import androidx.lifecycle.ViewModel
import com.aliceresponde.themovieboard.di.ViewModelKey
import com.aliceresponde.themovieboard.ui.main.movie.MoviesViewModel
import com.aliceresponde.themovieboard.ui.main.serie.SeriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeriesViewModel::class)
    abstract fun bindSeriesViewModel(viewModel: SeriesViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel::class)
//    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}
