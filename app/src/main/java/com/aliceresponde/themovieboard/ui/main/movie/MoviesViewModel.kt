package com.aliceresponde.themovieboard.ui.main.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(val repository : MoviesRepository): ViewModel() {

    fun getPopularMovies(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getPopularMovies()
            }
        }
    }



}
