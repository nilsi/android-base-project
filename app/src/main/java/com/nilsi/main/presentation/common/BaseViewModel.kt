package com.nilsi.main.presentation.common

import androidx.lifecycle.ViewModel
import com.main.data.ServerError
import com.main.data.ServerError.BadCredentials
import com.main.data.ServerError.ConnectException
import com.nilsi.main.presentation.common.UiEvent.ShowMessage
import com.nilsi.main.R
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<UI_STATE : UiState>(private val initialState: UI_STATE) : ViewModel() {

    private val eventChannel = MutableSharedFlow<UiEvent>()
    private val uiState = MutableStateFlow(initialState)

    fun setState(state: UI_STATE) {
        uiState.value = state
    }

    suspend fun sendEvent(event: UiEvent) {
        eventChannel.emit(event)
    }

    fun getState(): StateFlow<UI_STATE> {
        return uiState
    }

    fun getEvents(): Flow<UiEvent> {
        return eventChannel.asSharedFlow()
    }

    suspend fun handleDefaultError(error: ServerError) {
        setState(initialState)
        when (error) {
            ConnectException -> sendEvent(ShowMessage(R.string.no_internet))
            BadCredentials -> sendEvent(ShowMessage(R.string.bad_credentials))
            else -> sendEvent(ShowMessage(R.string.error_unknown))
        }
    }
}

