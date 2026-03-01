package com.elouazzani.data.repositories

import com.elouazzani.core.utils.Results
import com.elouazzani.data.apiservices.ProductApiService
import com.elouazzani.data.mapper.toDomain
import com.elouazzani.domain.model.Product
import com.elouazzani.domain.model.ProductReview
import com.elouazzani.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class ProductRepositoryImpl(
    private val productApiService: ProductApiService
) : ProductRepository {
    override suspend fun getAllProducts(): Flow<Results<List<Product>>> = flow {

        try {
            val response = productApiService.getProducts()
            val domainProducts = response.map { it.toDomain() }
            emit(Results.Success(domainProducts))
        } catch (e: IOException) {
            emit(Results.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Results.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override suspend fun getProductReviews(): Flow<Results<List<ProductReview>>> = flow {
        try {
            val response = productApiService.getProductReviews().map {
                it.toDomain()
            }
            emit(Results.Success(response))
        } catch (e: IOException) {
            emit(Results.Error("Couldn't reach server. Check your internet connection."))

        }
        catch (e: Exception) {
            emit(Results.Error(e.localizedMessage ?: "An unexpected error occurred"))

        }
    }
}