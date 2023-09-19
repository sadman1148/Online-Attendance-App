package com.bondstein.onlineattendanceapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("data")
    val data: List<Data>,

    @SerializedName("links")
    val links: Links,

    @SerializedName("meta")
    val meta: Meta
)