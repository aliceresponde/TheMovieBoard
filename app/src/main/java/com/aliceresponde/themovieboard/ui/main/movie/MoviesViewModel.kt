package com.aliceresponde.themovieboard.ui.main.movie

import android.view.View.GONE
import android.view.View.VISIBLE
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

    private var _recyclerVisibility: MutableLiveData<Int> = MutableLiveData(GONE)
    val recyclerVisibility: LiveData<Int> get() = _recyclerVisibility

    private var _noDataVisibility: MutableLiveData<Int> = MutableLiveData(GONE)
    val noDataVisibility: LiveData<Int> get() = _noDataVisibility

    private val _internetConection = MutableLiveData(true)
    val isInternetOn: LiveData<Boolean> get() = _internetConection

    private val _movies = MutableLiveData<List<ShowItem>>()
    val movies: LiveData<List<ShowItem>> get() = _movies

    private suspend fun fetchPopularMovies() {
        try {
            _internetConection.postValue(true)
            repository.fetchPopularMovies()
        } catch (e: NoInternetException) {
            _internetConection.postValue(false)
            getPopularMovies()
        }

    }

    private suspend fun fetchRatedMovies() {
        try {
            _internetConection.postValue(true)
            repository.fetchPopularMovies()
        } catch (e: NoInternetException) {
            _internetConection.postValue(false)
            getRatedMovies()
        }
    }

    fun getRatedMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchRatedMovies()
                val ratedMmovies = repository.getRatedMovies().map { it.toShowItem() }
                _movies.postValue(ratedMmovies)
                updateLayout(ratedMmovies)
            }
        }
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchPopularMovies()
                val popularMovies = repository.getPopularMovies().map { it.toShowItem() }
                _movies.postValue(popularMovies)
                updateLayout(popularMovies)
            }
        }
    }

    fun getMoviesByName(name: String) {
        if (name.isEmpty())
            return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _internetConection.postValue(true)
                    repository.fetchMoviesByName(name)
                    val byName = repository.getMovieByName(name).map { it.toShowItem() }
                    _movies.postValue(byName)
                    updateLayout(byName)
                } catch (e: NoInternetException) {
                    _internetConection.postValue(false)
                    val byName = repository.getMovieByName(name).map { it.toShowItem() }
                    _movies.postValue(byName)
                    updateLayout(byName)
                }
            }
        }
    }

    private fun updateLayout(data: List<ShowItem>) {
        if (data.isEmpty()) {
            _recyclerVisibility.postValue(GONE)
            _noDataVisibility.postValue(VISIBLE)
        } else {
            _recyclerVisibility.postValue(VISIBLE)
            _noDataVisibility.postValue(GONE)
        }
    }
}

