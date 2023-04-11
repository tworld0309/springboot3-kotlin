package com.boot3kotlin.data.repository

import com.boot3kotlin.data.entity.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long> {
    fun findAllBy(): List<Product>
}