package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.common.arch.ActionProcessor
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.model.Event
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

val fakeUsers = listOf(
    UserDataModel(
        uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
        gender = "male",
        title = "Mr",
        firstname = "John",
        lastname = "Doe",
        pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
        nationality = "US",
        age = 27,
    ),
    UserDataModel(
        uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
        gender = "female",
        title = "Mrs",
        firstname = "Jane",
        lastname = "Doe",
        pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
        nationality = "FR",
        age = 35,
    ),
)

fun fakeActionProcessor(
    isConnectionLost: Boolean,
    users: List<UserDataModel>?,
) = object : ActionProcessor<Action, Mutation, Event> {
    override fun invoke(action: Action): Flow<Pair<Mutation?, Event?>> =
        flow {
            when (action) {
                Action.CheckConnectivity -> {
                    emit(
                        if (isConnectionLost) {
                            Mutation.ShowLostConnection
                        } else {
                            Mutation.DismissLostConnection
                        } to null
                    )
                }
                is Action.EmailClick -> {
                    emit(null to Event.OpenEmail("john.doe@example.com"))
                }
                Action.Load, Action.Reload -> {
                    emit(Mutation.ShowLoader to null)
                    emit(
                        (users?.let(Mutation::ShowContent)
                            ?: Mutation.ShowError(Exception("error message")))
                                to null
                    )
                }
                is Action.PhoneClick -> {
                    emit(null to Event.OpenPhone("0123456789"))
                }
            }
        }
}