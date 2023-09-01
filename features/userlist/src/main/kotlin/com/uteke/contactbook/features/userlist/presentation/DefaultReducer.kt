package com.uteke.contactbook.features.userlist.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.common.arch.Reducer
import com.uteke.contactbook.features.userlist.R
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import com.uteke.contactbook.features.userlist.presentation.view.UserState
import com.uteke.contactbook.features.userlist.presentation.view.ViewState
import java.lang.Exception

class DefaultReducer(
    private val resources: Resources,
) : Reducer<Mutation, ViewState> {
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
            errorMessage = resources.getString(R.string.userlist_text_generic_error)
                .format(exception.message),
        )

    private fun ViewState.mutateToShowLostConnection() =
        copy(isConnectivityVisible = true)

    private fun ViewState.mutateToShowLoader() =
        copy(isLoaderVisible = true)

    private fun UserDataModel.toUserState()  =
        UserState(
            uuid = uuid,
            gender = gender,
            fullname = resources.getString(R.string.userlist_text_fullname_format)
                .format(title, firstname, lastname),
            pictureUrl = pictureUrl,
            age = resources.getString(R.string.userlist_text_age_format)
                .format(age),
            flag = nationality.toFlag(),
        )

    private fun String.toFlag(): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41

        val firstChar = Character.codePointAt(this, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(this, 1) - asciiOffset + flagOffset

        return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    }
}