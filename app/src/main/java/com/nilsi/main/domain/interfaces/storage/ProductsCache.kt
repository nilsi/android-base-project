package com.nilsi.main.domain.interfaces.storage

import com.nilsi.main.domain.entity.ProductEntity

interface ProductsCache {
    fun remove(id: String)
    fun add(product: ProductEntity)
}