package com.example.rideexpress.Home

import com.google.gson.annotations.SerializedName

data class RideResponse(
    @SerializedName("_id")
    val id: String,

    val name: String,
    val phone: String,
    val serviceType: String,
    val notes: String,

    @SerializedName("createdAt")
    val createdAt: String
)
