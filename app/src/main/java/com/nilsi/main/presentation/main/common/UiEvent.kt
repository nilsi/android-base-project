package com.main.presentation.main.common

sealed class UiEvent {
    data class ShowMessage(val message: Int) : UiEvent()
}
