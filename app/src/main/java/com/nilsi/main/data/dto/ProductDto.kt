package com.nilsi.main.data.dto

import com.nilsi.main.domain.entity.ProductEntity

class ProductDto {
}

fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity()
}
