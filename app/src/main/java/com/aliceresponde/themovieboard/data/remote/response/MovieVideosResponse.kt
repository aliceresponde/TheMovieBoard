package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName

class MovieVideosResponse(
    val id: String,
    val results: List<MovieVideoResult>
)
data class MovieVideoResult(
    @SerializedName("key")
    val videoId: String
)