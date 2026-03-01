package com.elouazzani.domain.model

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val brandName: String,
    val reviews: List<Review> ? = null
)