package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName


class SerieVideoResponse(
    val id: String,
    @SerializedName("results")
    val serieVideos: List<SerieVideoResult>
)

data class SerieVideoResult(
    @SerializedName("key")
    val videoId: String
)
