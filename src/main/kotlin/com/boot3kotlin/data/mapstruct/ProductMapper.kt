package com.boot3kotlin.data.mapstruct

import com.boot3kotlin.data.dto.ReadProductDTO
import com.boot3kotlin.data.entity.Product
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ProductMapper {
    @Mappings(
        Mapping(target = "id", expression = "java(product.getId())"),
        Mapping(target = "name", expression = "java(product.getName())"),
        Mapping(target = "category", expression = "java(product.getCategory())")
    )
    fun toDto(product: Product): ReadProductDTO
}