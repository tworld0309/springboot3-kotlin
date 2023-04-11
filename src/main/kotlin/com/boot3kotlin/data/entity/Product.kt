package com.boot3kotlin.data.entity

import com.boot3kotlin.data.dto.CreateProductDTO
import com.boot3kotlin.data.dto.ReadProductDTO
import jakarta.persistence.*
import java.time.OffsetDateTime

enum class Category {
    Phone, Laptop, Keyboard
}

@Entity
data class Product (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @Enumerated(EnumType.STRING)
    val category: Category,
    val createDateTime: OffsetDateTime = OffsetDateTime.now(),
    var updateDateTime: OffsetDateTime? = null
) {
    fun toReadProductDTO(): ReadProductDTO {
        return ReadProductDTO(
            id = id,
            name = name,
            category =  category
        )
    }

    fun toCreateProductDTO(): CreateProductDTO {
        return CreateProductDTO(
            name = name,
            category = category
        )
    }
}
