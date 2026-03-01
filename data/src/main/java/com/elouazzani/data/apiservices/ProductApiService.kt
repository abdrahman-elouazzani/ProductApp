package com.elouazzani.data.apiservices

import ProductDto
import com.elouazzani.data.dto.ProductReviewDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("testProject/items.json")
    suspend fun getProducts(): List<ProductDto>

    @GET("testProject/reviews.json")
    suspend fun getProductReviews(): List<ProductReviewDto>

}