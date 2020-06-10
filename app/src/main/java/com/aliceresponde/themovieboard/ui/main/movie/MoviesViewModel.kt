package com.aliceresponde.themovieboard.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliceresponde.themovieboard.data.repository.MoviesRepository
import com.aliceresponde.themovieboard.movietoShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val repository: MoviesRepository) : ViewModel() {


    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    val popularMovies = repository.getPopularMovies().movietoShow()

    val ratedMovies = repository.getRatedMovies().movietoShow()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _isViewLoading.value = true
            withContext(Dispatchers.IO) { repository.syncPopularMovies() }
            _isViewLoading.value = false
        }
    }

    fun fetchRatedMovies() {
        viewModelScope.launch {
            _isViewLoading.value = true
            withContext(Dispatchers.IO) { repository.syncPopularMovies() }
            _isViewLoading.value = false
        }
    }

    fun findMoviesByName(name: String) = repository.getMovieByName(name).movietoShow()
}

