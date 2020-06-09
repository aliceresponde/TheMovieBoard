package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName


class SerieDetailResponse(
    val id: String,
    val results: List<Results>
) {

    data class Results(
        @SerializedName("key")
        val videoId: String
    )

}

