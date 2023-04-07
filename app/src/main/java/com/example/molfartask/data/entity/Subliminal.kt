package com.example.molfartask.data.entity

import com.google.gson.annotations.SerializedName

data class Subliminal(
    @SerializedName("records")
    val record: List<Record>
)