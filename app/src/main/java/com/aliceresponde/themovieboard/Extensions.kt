package com.aliceresponde.themovieboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aliceresponde.themovieboard.data.local.Movie
import com.aliceresponde.themovieboard.data.local.Serie
import com.aliceresponde.themovieboard.data.remote.response.MovieResult
import com.aliceresponde.themovieboard.ui.model.ShowItem

fun MovieResult.toMovieEntity(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        date = date,
        imageUrl = imageUrl,
        voteAverage = voteAverage,
        popularity = popularity
    )
}

fun MovieResult.toSerieEntity(): Serie {
    return Serie(
        id = id,
        name = title,
        overview = overview,
        date = date,
        imageUrl = imageUrl,
        voteAverage = voteAverage,
        popularity = popularity
    )
}


fun Movie.toShowItem(): ShowItem {
    return ShowItem(
        id = id,
        name = title,
        date = date,
        imageUrl = BuildConfig.IMG_URL + imageUrl,
        overview = overview,
        videoKey = videoKey
    )
}

fun Serie.toShowItem(): ShowItem {
    return ShowItem(
        id = id,
        name = name,
        date = date,
        imageUrl = BuildConfig.IMG_URL + imageUrl,
        overview = overview,
        videoKey = videoKey
    )
}

fun LiveData<List<Movie>>.movietoShow(): LiveData<List<ShowItem>> =
    Transformations.map(this) {
        it.map { movie -> movie.toShowItem() }
    }

fun LiveData<List<Serie>>.serietoShow(): LiveData<List<ShowItem>> =
    Transformations.map(this) {
        it.map { serie -> serie.toShowItem() }
    }

