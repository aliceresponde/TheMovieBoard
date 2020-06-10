package com.aliceresponde.themovieboard.ui.main.serie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliceresponde.themovieboard.data.repository.MoviesRepository

class SeriesViewModelFactory(val respository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepository::class.java).newInstance(respository)
    }
}
