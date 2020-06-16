package com.aliceresponde.themovieboard.ui.main.serie

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliceresponde.themovieboard.data.remote.NoInternetException
import com.aliceresponde.themovieboard.data.repository.SeriesRepository
import com.aliceresponde.themovieboard.toShowItem
import com.aliceresponde.themovieboard.ui.model.ShowItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeriesViewModel(val repository: SeriesRepository) : ViewModel() {

    private var _recyclerVisibility: MutableLiveData<Int> = MutableLiveData(GONE)
    val recyclerVisibility: LiveData<Int> get() = _recyclerVisibility

    private var _noDataVisibility: MutableLiveData<Int> = MutableLiveData(GONE)
    val noDataVisibility: LiveData<Int> get() = _noDataVisibility

    private val _isInternetOn = MutableLiveData(true)
    val isInternetOn: LiveData<Boolean> get() = _isInternetOn

    private val _movies = MutableLiveData<List<ShowItem>>()
    val movies: LiveData<List<ShowItem>> get() = _movies

    private suspend fun fetchPopularSeries() {
        try {
            _isInternetOn.postValue(true)
            repository.fetchPopularSerie()
        } catch (e: NoInternetException) {
            _isInternetOn.postValue(false)
            getPopularSeries()
        }
    }

    private suspend fun fetchRatedSeries() {
        try {
            _isInternetOn.postValue(true)
            repository.fetchRatedMovies()
        } catch (e: NoInternetException) {
            _isInternetOn.postValue(false)
            getRatedSeries()
        }
    }

    fun getRatedSeries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchRatedSeries()
                val ratedMovies = repository.getRatedSerie().map { it.toShowItem() }
                _movies.postValue(ratedMovies)
                updateLayout(ratedMovies)
            }
        }
    }


    fun getPopularSeries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchPopularSeries()
                val popularMovies = repository.getPopularSerie().map { it.toShowItem() }
                _movies.postValue(popularMovies)
                updateLayout(popularMovies)
            }
        }
    }

    fun getSeriesByName(name: String) {
        if (name.isEmpty())
            return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _isInternetOn.postValue(true)
                    repository.fetchSerieByName(name)
                    val byName = repository.getSerieByName(name).map { it.toShowItem() }
                    _movies.postValue(byName)
                    updateLayout(byName)
                } catch (e: NoInternetException) {
                    _isInternetOn.postValue(false)
                    val byName = repository.getSerieByName(name).map { it.toShowItem() }
                    _movies.postValue(byName)
                    updateLayout(byName)
                }
            }
        }
    }

    private fun updateLayout(data: List<ShowItem>) {
        if (data.isNullOrEmpty()) {
            _recyclerVisibility.postValue(GONE)
            _noDataVisibility.postValue(VISIBLE)
        } else {
            _recyclerVisibility.postValue(VISIBLE)
            _noDataVisibility.postValue(GONE)
        }
    }
}
