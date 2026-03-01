package com.elouazzani.domain.repositories

import com.elouazzani.core.utils.Results
import com.elouazzani.domain.model.Product
import com.elouazzani.domain.model.ProductReview
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllProducts(): Flow<Results<List<Product>>>

    suspend fun getProductReviews(): Flow<Results<List<ProductReview>>>
}