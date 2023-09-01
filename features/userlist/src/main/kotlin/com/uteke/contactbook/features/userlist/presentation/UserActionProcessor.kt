package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.common.arch.ActionProcessor
import com.uteke.contactbook.features.userlist.data.GetUserContactRepository
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.model.Event
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class UserActionProcessor(
    private val getUserContactRepository: GetUserContactRepository,
) : ActionProcessor<Action, Mutation, Event> {

    override fun invoke(action: Action): Flow<Pair<Mutation?, Event?>> =
        flow {
            when (action) {
                is Action.EmailClick -> emailClick(uuid = action.uuid)
                is Action.PhoneClick -> phoneClick(uuid = action.uuid)
                else -> {
                    //no-op
                }
            }
        }

    private suspend fun FlowCollector<Pair<Mutation?, Event?>>.emailClick(uuid: String) {
        try {
            val result = getUserContactRepository(uuid = uuid)
            emit(null to Event.OpenEmail(email = result.email))
        } catch (exception: Exception) {
            emit(null to Event.ToastError(message = exception.message ?: ""))
        }
    }

    private suspend fun FlowCollector<Pair<Mutation?, Event?>>.phoneClick(uuid: String) {
        try {
            val result = getUserContactRepository(uuid = uuid)
            val phone = result.cell.takeIf { it.isNotEmpty() } ?: result.phone
            emit(null to Event.OpenPhone(phone = phone))
        } catch (exception: Exception) {
            emit(null to Event.ToastError(message = exception.message ?: ""))
        }
    }
}