package com.uteke.contactbook.features.userdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepository
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserByIdRepository: GetUserByIdRepository,
    private val userDetailsStateMapper: UserDetailsStateMapper,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {
    private val _viewStateFlow: MutableStateFlow<UserDetailsViewState> =
        MutableStateFlow(UserDetailsViewState.Loading)
    val viewStateFlow: StateFlow<UserDetailsViewState> = _viewStateFlow

    fun process(action: UserDetailsAction) {
        when (action) {
            is UserDetailsAction.Load -> load(id = action.id)
        }
    }

    private fun load(id: String) {
        viewModelScope.launch(dispatcherProvider.default) {
            _viewStateFlow.update { UserDetailsViewState.Loading }

            try {
                val result = getUserByIdRepository.invoke(id)
                _viewStateFlow.update { userDetailsStateMapper.map(user = result) }
            } catch (exception: NoUserForIdException) {
                _viewStateFlow.update { userDetailsStateMapper.map(exception = exception) }
            } catch (exception: Exception) {
                _viewStateFlow.update { userDetailsStateMapper.map(exception = exception) }
            }
        }
    }
}