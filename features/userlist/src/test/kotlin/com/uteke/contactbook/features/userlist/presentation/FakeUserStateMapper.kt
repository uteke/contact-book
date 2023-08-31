package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.UserListStateMapper
import com.uteke.contactbook.features.userlist.presentation.UserListViewState
import com.uteke.contactbook.features.userlist.presentation.UserState
import java.lang.Exception

internal val fakeUserListStateMapper = object : UserListStateMapper {
    override fun map(
        content: UserListViewState.Content,
        users: List<UserDataModel>,
    ): UserListViewState.Content =
        content.copy(
            isLoading = false,
            userStates = content.userStates.toMutableList().apply {
                addAll(
                    users.map { user ->
                        with(user) {
                            UserState(
                                uuid = user.uuid,
                                gender = gender,
                                fullname = "$title $firstname $lastname",
                                pictureUrl = pictureUrl,
                                age = "$age ans",
                                flag = nationality,
                            )
                        }
                    },
                )
            }
        )

    override fun map(viewState: UserListViewState, exception: Exception): UserListViewState.Error =
        UserListViewState.Error(
            isConnectionLost = viewState.isConnectionLost,
            message = "generic error",
        )
}