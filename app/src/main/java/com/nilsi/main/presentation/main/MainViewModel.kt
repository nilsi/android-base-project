package com.nilsi.main.presentation.main

import androidx.lifecycle.viewModelScope
import com.main.data.Either
import com.main.presentation.main.common.UiEvent.ShowMessage
import com.nilsi.main.R
import com.nilsi.main.domain.interfaces.repository.ProductRepository
import com.nilsi.main.presentation.main.MainUiState.*
import com.nilsi.main.presentation.main.common.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val productRepository: ProductRepository) :
    BaseViewModel<MainUiState>(Initial) {

    fun fetchProduct() = viewModelScope.launch {
        setState(Loading)

        productRepository.getProduct("").collect {
            when (it) {
                is Either.Left -> {
                    sendEvent(ShowMessage(R.string.error_unknown))
                }
                is Either.Right -> {
                    setState(ProductFetched)
                }
            }
        }

    }
}

