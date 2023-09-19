package com.bondstein.onlineattendanceapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("from")
    val from: Int,

    @SerializedName("last_page")
    val lastPage: Int,

    @SerializedName("path")
    val path: String,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("to")
    val to: Int,

    @SerializedName("total")
    val total: Int
)