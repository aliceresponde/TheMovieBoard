package com.aliceresponde.themovieboard.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import com.aliceresponde.themovieboard.toShowItem
import com.aliceresponde.themovieboard.ui.model.ShowItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val repository: MoviesRepository) : ViewModel() {

    private val _internetConection = MutableLiveData(true)
    val isInternetOn: LiveData<Boolean> get() = _internetConection

    private val _movies = MutableLiveData<List<ShowItem>>()
    val movies: LiveData<List<ShowItem>> get() = _movies

    init {
        fetchPopularMovies()
        fetchRatedMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                _internetConection.value = true
                withContext(Dispatchers.IO) { repository.fetchPopularMovies() }
            } catch (e: NoInternetException) {
                _internetConection.value = false
                getPopularMovies()
            }
        }
    }

    private fun fetchRatedMovies() {
        viewModelScope.launch {
            try {
                _internetConection.value = true
                withContext(Dispatchers.IO) { repository.fetchPopularMovies() }
            } catch (e: NoInternetException) {
                _internetConection.value = false
                getRatedMpvies()
            }
        }
    }

    fun getRatedMpvies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ratedMmovies = repository.getRatedMovies().map { it.toShowItem() }
                _movies.postValue(ratedMmovies)
            }
        }
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val popularMovies = repository.getPopularMovies().map { it.toShowItem() }
                _movies.postValue(popularMovies)
            }
        }
    }

    fun fetchMoviesByName(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _internetConection.postValue(true)
                    repository.fetchMoviesByName(name)
                    val byName = repository.getMovieByName(name).map { it.toShowItem() }
                    _movies.postValue(byName)
                } catch (e: NoInternetException) {
                    _internetConection.postValue(false)
                    val byName = repository.getMovieByName(name).map { it.toShowItem() }
                    _movies.postValue(byName)
                }
            }
        }
    }
}

