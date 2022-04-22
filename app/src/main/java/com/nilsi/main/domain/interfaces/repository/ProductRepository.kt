package com.nilsi.main.domain.interfaces.repository

import com.main.data.Either
import com.main.data.ServerError
import com.nilsi.main.domain.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProduct(id: String): Flow<Either<ServerError, ProductEntity>>
}
