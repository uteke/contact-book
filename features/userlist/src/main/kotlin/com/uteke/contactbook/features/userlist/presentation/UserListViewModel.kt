package com.uteke.contactbook.features.userlist.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import com.uteke.contactbook.features.common.monitor.ConnectivityStatus
import com.uteke.contactbook.features.common.monitor.ConnectivityMonitor
import com.uteke.contactbook.features.userlist.data.GetUserListRepository
import com.uteke.contactbook.features.userlist.data.model.GetUserListException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserListRepository: GetUserListRepository,
    private val userListStateMapper: UserListStateMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val connectivityMonitor: ConnectivityMonitor,
) : ViewModel() {
    private var currentPage by mutableStateOf(value = 0)
    private val _viewStateFlow: MutableStateFlow<UserListViewState> =
        MutableStateFlow(UserListViewState.Content(isLoading = true))

    val viewStateFlow: StateFlow<UserListViewState> = _viewStateFlow

    init {
        viewModelScope.launch(dispatcherProvider.default) {
            connectivityMonitor.statusFlow
                .collectLatest { status ->
                    val connected = status == ConnectivityStatus.AVAILABLE

                    _viewStateFlow.update { currentState ->
                        when (currentState) {
                            is UserListViewState.Content ->
                                currentState.copy(isConnectionLost = connected.not())
                            is UserListViewState.Error ->
                                currentState.copy(isConnectionLost = connected.not())
                        }
                    }
                }
        }
    }

    fun process(action: UserListAction) {
        when (action) {
            UserListAction.Load -> load(page = currentPage + 1)
            UserListAction.Reload -> load(page = currentPage)
        }
    }

    private fun load(page: Int) {
        viewModelScope.launch(dispatcherProvider.default) {
            _viewStateFlow.update { currentState ->
                (currentState as? UserListViewState.Content)?.copy(isLoading = true)
                    ?: UserListViewState.Content(
                        isLoading = true,
                        isConnectionLost = currentState.isConnectionLost,
                    )
            }

            try {
                val result = getUserListRepository.invoke(page = page, limit = RESULT_LIMIT)
                if (result.users.isNotEmpty()) {
                    currentPage = result.page
                }

                _viewStateFlow.update { currentState ->
                    userListStateMapper.map(
                        content = (currentState as UserListViewState.Content),
                        users = result.users,
                    )
                }
            } catch (exception: GetUserListException) {
                _viewStateFlow.update { currentState ->
                    userListStateMapper.map(
                        viewState = currentState,
                        exception = exception
                    )
                }
            }
        }
    }

    private companion object {
        const val RESULT_LIMIT = 20
    }
}