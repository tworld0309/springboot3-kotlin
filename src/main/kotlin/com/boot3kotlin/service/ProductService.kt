package com.boot3kotlin.service

import com.boot3kotlin.data.dto.CreateProductDTO
import com.boot3kotlin.data.dto.ReadProductDTO
import com.boot3kotlin.data.mapstruct.ProductMapper
import com.boot3kotlin.data.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ProductService (
    val productMapper: ProductMapper
){

    @Autowired
    lateinit var productRepository: ProductRepository

    fun getProducts(): List<ReadProductDTO> {
        val product = productRepository.findAll()
        return product.map { it.toReadProductDTO() }
    }

    @Transactional(readOnly = true)
    fun getProduct(id : Long): ReadProductDTO {
        return productMapper.toDto(productRepository.findById(id).orElseThrow({ Exception("no data")}));
    }

    @Transactional
    fun createProduct(createProductDTO: CreateProductDTO): CreateProductDTO {
        val product = productRepository.save(createProductDTO.toEntity())
        return product.toCreateProductDTO()
    }
}