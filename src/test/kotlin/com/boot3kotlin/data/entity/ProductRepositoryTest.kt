package com.boot3kotlin.data.entity

import com.boot3kotlin.data.repository.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    lateinit var productRepository: ProductRepository

    lateinit var product: Product

    @BeforeEach
    fun init() {
        product = Product(
            id = 1,
            name= "iPhone",
            category= Category.Phone
        )
        product = productRepository.save(product)
    }

    @Test
    fun findById() {
        val fromDB = productRepository.findById(product.id!!)
        Assertions.assertThat(fromDB.get().name).isEqualTo("iPhone")
    }
}
