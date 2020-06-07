package com.aliceresponde.themovieboard.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowItem(
    val name: String,
    val date: String,
    val overview: String,
    val imageUrl: String
) : Parcelable