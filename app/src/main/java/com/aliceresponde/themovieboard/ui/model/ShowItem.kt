package com.aliceresponde.themovieboard.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowItem(
    val id: Int,
    val name: String,
    val date: String,
    val overview: String,
    val imageUrl: String,
    val videoKey: String
) : Parcelable {
    override fun equals(other: Any?): Boolean =
        if (other is ShowItem) {
            id == other.id
                    && name == other.name
                    && date == other.date
                    && overview == other.overview
                    && imageUrl == other.imageUrl
                    && videoKey == other.videoKey
        } else
            false
}