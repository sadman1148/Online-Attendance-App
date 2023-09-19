package com.bondstein.onlineattendanceapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("address")
    val address: String
)