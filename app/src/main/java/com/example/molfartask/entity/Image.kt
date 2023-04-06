package com.example.molfartask.entity

data class Image(
    val filename: String,
    val height: Int,
    val id: String,
    val size: Int,
    val thumbnails: Thumbnails,
    val type: String,
    val url: String,
    val width: Int
)