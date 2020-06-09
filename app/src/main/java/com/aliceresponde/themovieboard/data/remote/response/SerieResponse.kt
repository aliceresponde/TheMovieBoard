package com.aliceresponde.themovieboard.data.remote.response

import com.google.gson.annotations.SerializedName

data class SerieResponse(
    @SerializedName("results")
    val series : List<SerieResult>
)