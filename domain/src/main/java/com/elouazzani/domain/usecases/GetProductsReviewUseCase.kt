package com.elouazzani.domain.usecases

import com.elouazzani.core.utils.Results
import com.elouazzani.domain.model.ProductReview
import com.elouazzani.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsReviewUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Flow<Results<List<ProductReview>>> {
        return repository.getProductReviews()
    }
}