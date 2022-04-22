package com.nilsi.main.data.api

import com.nilsi.main.data.dto.ProductDto

interface ServiceXApi {
    suspend fun fetchProduct(id: String): ProductDto
}

class ServiceXApiImpl : ServiceXApi {
    override suspend fun fetchProduct(id: String): ProductDto {
        return ProductDto()
    }
}
