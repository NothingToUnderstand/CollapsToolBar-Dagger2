package com.example.molfartask.data.entity

import com.google.gson.annotations.SerializedName

data class Record(
    val createdTime: String,
    @SerializedName("fields")
    val field: Field,
    val id: String
)