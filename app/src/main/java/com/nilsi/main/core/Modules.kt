package com.nilsi.main.core

import com.nilsi.main.data.api.ServiceXApi
import com.nilsi.main.data.api.ServiceXApiImpl
import com.nilsi.main.data.repository.ProductRepositoryImpl
import com.nilsi.main.data.storage.ProductCacheImpl
import com.nilsi.main.domain.interfaces.repository.ProductRepository
import com.nilsi.main.domain.interfaces.storage.ProductsCache
import com.nilsi.main.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val networkModule = module {
    fun provideNetworkApi(): ServiceXApi {
        return ServiceXApiImpl()
    }

    single { provideNetworkApi() }
}

val storageModule = module {
    fun provideProductCache(): ProductsCache {
        return ProductCacheImpl()
    }

    single { provideProductCache() }
}