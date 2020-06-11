package com.aliceresponde.themovieboard.ui.main.serie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliceresponde.themovieboard.data.remote.response.SerieResponse
import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import com.aliceresponde.themovieboard.data.repository.SeriesRepository

class SeriesViewModelFactory(val respository: SeriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SeriesRepository::class.java).newInstance(respository)
    }
}
