package com.elouazzani.data.dto

import com.google.gson.annotations.SerializedName

data class ProductReviewDto(
    @SerializedName("product_id")
    val productId: Long,
    val hide: Boolean,
    val reviews: List<ReviewDto>
)

data class ReviewDto(
    val name: String?,
    val text: String?,
    val rating: Double?
)