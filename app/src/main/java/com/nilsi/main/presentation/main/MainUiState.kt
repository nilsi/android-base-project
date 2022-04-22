package com.nilsi.main.presentation.main

import com.main.presentation.main.common.UiState

sealed class MainUiState : UiState {
    object Initial : MainUiState()
    object Loading : MainUiState()
    object ProductFetched: MainUiState()
}
