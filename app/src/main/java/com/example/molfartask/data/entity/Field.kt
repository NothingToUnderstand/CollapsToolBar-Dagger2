package com.example.molfartask.data.entity

import com.google.gson.annotations.SerializedName

data class Field(
    val about: String,
    @SerializedName("categories")
    val category: List<String>,
    val id: Int,
    val image: List<Image>,
    val title: String,
)