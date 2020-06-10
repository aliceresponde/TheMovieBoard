package com.aliceresponde.themovieboard.ui.main.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import javax.inject.Inject

class MoviesViewModelFactory @Inject constructor (val repository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepository::class.java).newInstance(repository)
    }
}
