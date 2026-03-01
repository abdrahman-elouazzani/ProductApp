package com.elouazzani.domain.model

data class ProductReview(
    val productId: Long,
    val hide: Boolean,
    val reviews: List<Review>
)

data class Review(
    val name: String?,
    val text: String?,
    val rating: Double?
)
