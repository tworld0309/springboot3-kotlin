package com.boot3kotlin.controller

import com.boot3kotlin.data.dto.CreateProductDTO
import com.boot3kotlin.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @GetMapping("/products", produces = ["application/json"])
    fun getProducts(): ResponseEntity<Any> {
        return ResponseEntity
            .ok()
            .body(productService.getProducts())
    }

    @PostMapping("/product")
    fun createProduct(@RequestBody createProductDTO: CreateProductDTO): ResponseEntity<Any> {
        productService.createProduct(createProductDTO)
        return ResponseEntity
            .ok()
            .body(true)
    }
}