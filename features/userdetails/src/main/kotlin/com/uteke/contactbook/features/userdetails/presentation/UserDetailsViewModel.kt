package com.uteke.contactbook.features.userdetails.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepository
import com.uteke.contactbook.features.userdetails.data.GetUserContactRepository
import com.uteke.contactbook.features.userdetails.data.GetUserLocationRepository
import com.uteke.contactbook.features.userdetails.presentation.model.Action
import com.uteke.contactbook.features.userdetails.presentation.model.Event
import com.uteke.contactbook.features.userdetails.presentation.view.ViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserByIdRepository: GetUserByIdRepository,
    private val getUserContactRepository: GetUserContactRepository,
    private val getUserLocationRepository: GetUserLocationRepository,
    private val viewStateMapper: ViewStateMapper,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {
    private val _viewStateFlow: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    private val _eventChannel: Channel<Event> = Channel(BUFFERED)

    val viewStateFlow: Flow<ViewState> = _viewStateFlow
    val eventFlow: Flow<Event> = _eventChannel.receiveAsFlow()

    fun process(action: Action) {
        when (action) {
            is Action.Load -> load(id = action.id)
            is Action.EmailClick -> emailClick(id = action.id)
            is Action.LocationClick -> locationClick(id = action.id)
            is Action.PhoneClick -> phoneClick(id = action.id)
        }
    }

    private fun load(id: String) {
        viewModelScope.launch(dispatcherProvider.default) {
            _viewStateFlow.update { ViewState.Loading }

            try {
                val result = getUserByIdRepository.invoke(id)
                _viewStateFlow.update { viewStateMapper.map(user = result) }
            } catch (exception: Exception) {
                _viewStateFlow.update { viewStateMapper.map(exception = exception) }
            }
        }
    }

    private fun emailClick(id: String) {
        viewModelScope.launch(dispatcherProvider.default) {
            try {
                val result = getUserContactRepository.invoke(id)
                _eventChannel.trySend(Event.OpenEmail(email = result.email))
            } catch (exception: Exception) {
                Log.d("UserDetailsViewModel", exception.message ?: exception.toString())
            }
        }
    }

    private fun phoneClick(id: String) {
        viewModelScope.launch(dispatcherProvider.default) {
            try {
                val result = getUserContactRepository.invoke(id)
                val phone = result.cell.takeIf { it.isNotEmpty() } ?: result.phone
                _eventChannel.trySend(Event.OpenPhone(phone = phone))
            } catch (exception: Exception) {
                Log.d("UserDetailsViewModel", exception.message ?: exception.toString())
            }
        }
    }

    private fun locationClick(id: String) {
        viewModelScope.launch(dispatcherProvider.default) {
            try {
                val result = getUserLocationRepository.invoke(id)
                _eventChannel.trySend(
                    Event.OpenLocation(
                        latitude = result.latitude,
                        longitude = result.longitude,
                    )
                )
            } catch (exception: Exception) {
                Log.d("UserDetailsViewModel", exception.message ?: exception.toString())
            }
        }
    }
}