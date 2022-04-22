package com.nilsi.main.data.repository

import com.main.data.Either
import com.main.data.ServerError
import com.nilsi.main.data.api.ServiceXApi
import com.nilsi.main.data.dto.toProductEntity
import com.nilsi.main.domain.entity.ProductEntity
import com.nilsi.main.domain.interfaces.repository.ProductRepository
import com.nilsi.main.domain.interfaces.storage.ProductsCache
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val serviceApi: ServiceXApi,
    private val productsCache: ProductsCache
) : ProductRepository {

    override fun getProduct(id: String) = flow<Either<ServerError, ProductEntity>> {
        serviceApi.fetchProduct(id).run {
            val product = this.toProductEntity()
            productsCache.add(product)
            emit(Either.Right(product))
        }
    }.catch {
        emit(Either.Left(ServerError.ConnectException))
    }
}