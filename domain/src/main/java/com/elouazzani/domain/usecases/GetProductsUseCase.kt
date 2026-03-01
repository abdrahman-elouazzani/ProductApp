package com.elouazzani.domain.usecases

import com.elouazzani.core.utils.Results
import com.elouazzani.domain.model.Product
import com.elouazzani.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Flow<Results<List<Product>>> {
        return repository.getAllProducts()
    }
}