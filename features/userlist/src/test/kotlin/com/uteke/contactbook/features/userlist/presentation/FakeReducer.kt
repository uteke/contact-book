package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.common.arch.Reducer
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import com.uteke.contactbook.features.userlist.presentation.view.UserState
import com.uteke.contactbook.features.userlist.presentation.view.ViewState

val fakeReducer = object : Reducer<Mutation, ViewState> {
    override fun invoke(mutation: Mutation, currentState: ViewState): ViewState =
        when (mutation) {
            Mutation.DismissLostConnection ->
                currentState.mutateToDismissLostConnection()
            is Mutation.ShowContent ->
                currentState.mutateToShowContent(users = mutation.users)
            is Mutation.ShowError ->
                currentState.mutateToShowError(exception = mutation.exception)
            Mutation.ShowLoader ->
                currentState.mutateToShowLoader()
            Mutation.ShowLostConnection ->
                currentState.mutateToShowLostConnection()
        }

    private fun ViewState.mutateToDismissLostConnection() =
        copy(isConnectivityVisible = false)

    private fun ViewState.mutateToShowContent(users: List<UserDataModel>) =
        copy(
            isLoaderVisible = false,
            isUserListVisible = true,
            userStates = userStates.toMutableList().apply {
                addAll(users.map { it.toUserState() })
            },
            isErrorVisible = false,
        )

    private fun ViewState.mutateToShowError(exception: Exception) =
        copy(
            isLoaderVisible = false,
            isUserListVisible = false,
            isErrorVisible = true,
            errorMessage = "generic error by %s".format(exception.message),
        )

    private fun ViewState.mutateToShowLostConnection() =
        copy(isConnectivityVisible = true)

    private fun ViewState.mutateToShowLoader() =
        copy(isLoaderVisible = true)

    private fun UserDataModel.toUserState()  =
        UserState(
            uuid = uuid,
            gender = gender,
            fullname = "%s %s %s"
                .format(title, firstname, lastname),
            pictureUrl = pictureUrl,
            age = "%d y/o".format(age),
            flag = nationality,
        )
}