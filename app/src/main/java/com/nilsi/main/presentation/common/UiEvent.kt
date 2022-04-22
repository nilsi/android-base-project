package com.nilsi.main.presentation.common

sealed class UiEvent {
    data class ShowMessage(val message: Int) : UiEvent()
}
