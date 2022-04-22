package com.nilsi.main.data.storage

import com.nilsi.main.domain.entity.ProductEntity
import com.nilsi.main.domain.interfaces.storage.ProductsCache

class ProductCacheImpl : ProductsCache {
    override fun remove(id: String) {
        // remove to cache
    }

    override fun add(product: ProductEntity) {
        // add to cache
    }
}