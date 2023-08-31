package com.uteke.contactbook.features.userlist.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.userlist.R
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import java.lang.Exception

interface UserListStateMapper {
    fun map(content: UserListViewState.Content, users: List<UserDataModel>): UserListViewState.Content
    fun map(viewState: UserListViewState, exception: Exception): UserListViewState.Error
}

class UserListStateMapperImpl(
    private val resources: Resources,
) : UserListStateMapper {
    override fun map(
        content: UserListViewState.Content,
        users: List<UserDataModel>,
    ): UserListViewState.Content =
        content.copy(
            isLoading = false,
            userStates = content.userStates.toMutableList().apply {
                addAll(users.map { it.toUserState() })
            }
        )

    override fun map(viewState: UserListViewState, exception: Exception): UserListViewState.Error =
        UserListViewState.Error(
            isConnectionLost = viewState.isConnectionLost,
            message = resources.getString(R.string.userlist_text_generic_error),
        )

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