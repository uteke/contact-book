package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.common.arch.ActionProcessor
import com.uteke.contactbook.features.common.monitor.ConnectivityMonitor
import com.uteke.contactbook.features.common.monitor.ConnectivityStatus
import com.uteke.contactbook.features.userlist.data.GetUserListRepository
import com.uteke.contactbook.features.userlist.data.model.GetUserListException
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.model.Event
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DefaultActionProcessor(
    private val getUserListRepository: GetUserListRepository,
    private val connectivityMonitor: ConnectivityMonitor,
) : ActionProcessor<Action, Mutation, Event> {
    private var currentPage = 0

    override fun invoke(action: Action): Flow<Pair<Mutation?, Event?>> =
        if (action is Action.CheckConnectivity) {
            checkConnectivity()
        } else flow {
            when (action) {
                Action.Load -> load(page = currentPage + 1)
                Action.Reload -> load(page = currentPage)
                else -> {
                    //no-op
                }
            }
        }

    private fun checkConnectivity() =
        connectivityMonitor.statusFlow
            .map { status ->
                if (status == ConnectivityStatus.AVAILABLE) {
                    Mutation.DismissLostConnection
                } else {
                    Mutation.ShowLostConnection
                } to null
            }

    private suspend fun FlowCollector<Pair<Mutation?, Event?>>.load(page: Int) {
        emit(Mutation.ShowLoader to null)

        try {
            val result = getUserListRepository.invoke(page = page, limit = RESULT_LIMIT)
            if (result.users.isNotEmpty()) {
                currentPage = result.page
            }
            emit(Mutation.ShowContent(users = result.users) to null)
        } catch (exception: GetUserListException) {
            emit(Mutation.ShowError(exception = exception) to null)
        }
    }

    private companion object {
        const val RESULT_LIMIT = 20
    }
}