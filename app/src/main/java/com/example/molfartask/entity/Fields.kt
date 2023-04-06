package com.example.molfartask.entity

data class Fields(
    val about: String,
    val categories: List<String>,
    val id: Int,
    val image: List<Image>,
    val intention: String,
    val modules: String,
    val record_links: String,
    val record_titles: String,
    val title: String,
    val type: String
)