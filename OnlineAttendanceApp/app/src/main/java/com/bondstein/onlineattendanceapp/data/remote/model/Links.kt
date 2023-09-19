package com.bondstein.onlineattendanceapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String,

    @SerializedName("prev")
    val prev: Any,

    @SerializedName("next")
    val next: String
)
