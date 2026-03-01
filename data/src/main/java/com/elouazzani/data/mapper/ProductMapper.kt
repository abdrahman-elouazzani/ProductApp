package com.elouazzani.data.mapper

import ProductDto
import com.elouazzani.data.dto.ProductReviewDto
import com.elouazzani.data.dto.ReviewDto
import com.elouazzani.domain.model.Product
import com.elouazzani.domain.model.ProductReview
import com.elouazzani.domain.model.Review


fun ProductDto.toDomain(): Product {
    return Product(
        id = productId,
        name = productName,
        description = description,
        price = price,
        imageUrl = imagesUrl.large.ifEmpty { imagesUrl.small },
        brandName = brand.name
    )
}

fun ProductReviewDto.toDomain(): ProductReview {
    return ProductReview(
        productId = productId,
        hide = hide,
        reviews = reviews.map { it.toDomain() }

    )
}

fun ReviewDto.toDomain(): Review {
    return Review(
        name = name,
        text = text,
        rating = rating
    )
}