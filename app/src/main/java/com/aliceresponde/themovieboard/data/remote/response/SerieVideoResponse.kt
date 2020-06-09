package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName


class SerieVideoResponse(
    val id: String,
    val results: List<MovieVideoResult>
)

data class SerieVideoResult(
    @SerializedName("key")
    val videoId: String
)
