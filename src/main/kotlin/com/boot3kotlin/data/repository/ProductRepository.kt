package com.boot3kotlin.data.repository

import com.boot3kotlin.data.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
    fun findAllBy(): List<Product>
}