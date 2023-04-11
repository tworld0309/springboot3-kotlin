package com.boot3kotlin.data.dto

import com.boot3kotlin.data.entity.Category
import com.boot3kotlin.data.entity.Product

data class ReadProductDTO (
    val id: Long? = null,
    val name: String,
    val category: Category
)

data class CreateProductDTO (
    val name: String,
    val category: Category
) {
    fun toEntity(): Product {
        return Product(
            name = name,
            category = category
        )
    }
}