package com.aliceresponde.themovieboard.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieSerieDetail(
    val name: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String,
    val description: String
    ) : Parcelable