package com.aliceresponde.themovieboard

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aliceresponde.themovieboard.data.local.Movie
import com.aliceresponde.themovieboard.data.local.Serie
import com.aliceresponde.themovieboard.data.remote.response.MovieResult
import com.aliceresponde.themovieboard.data.remote.response.SerieResult
import com.aliceresponde.themovieboard.ui.model.ShowItem

fun MovieResult.toMovieEntity(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = if (overview.isNullOrEmpty()) "" else overview,
        date = if (date.isNullOrEmpty()) "" else date,
        imageUrl = if (imageUrl.isNullOrEmpty()) "" else imageUrl,
        voteAverage = voteAverage,
        popularity = popularity
    )
}

fun SerieResult.toSerieEntity(): Serie {
    return Serie(
        id = id,
        name = name,
        overview = if (overview.isNullOrEmpty()) "" else overview,
        date = date,
        imageUrl = if (imageUrl.isNullOrEmpty()) "" else imageUrl,
        voteAverage = voteAverage,
        popularity = popularity
    )
}


fun Movie.toShowItem(): ShowItem {
    return ShowItem(
        id = id,
        name = title,
        date = date,
        imageUrl = if (imageUrl != null) BuildConfig.IMG_URL + imageUrl else "",
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
        videoKey = BuildConfig.VIDEO_URL + videoKey
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

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}