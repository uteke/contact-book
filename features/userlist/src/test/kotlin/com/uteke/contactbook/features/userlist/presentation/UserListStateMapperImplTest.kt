package com.uteke.contactbook.features.userlist.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.userlist.R
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.UserListStateMapperImpl
import com.uteke.contactbook.features.userlist.presentation.UserListViewState
import com.uteke.contactbook.features.userlist.presentation.UserState
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.lang.Exception

class UserListStateMapperImplTest {
    private val resources = mockk<Resources>()
    private val mapper = UserListStateMapperImpl(resources = resources)

    @Test
    fun `map empty list with data list model to Content`() {
        every { resources.getString(R.string.userlist_text_fullname_format) } returns "%s %s %s"
        every { resources.getString(R.string.userlist_text_age_format) } returns "%d y/o"

        mapper.map(
            content = UserListViewState.Content(
                isLoading = true,
                userStates = emptyList(),
            ),
            users = listOf(
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
            ),
        ) shouldBe
                UserListViewState.Content(
                    isLoading = false,
                    userStates = listOf(
                        UserState(
                            uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                            gender = "female",
                            fullname = "Mrs Jane Doe",
                            pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                            flag = "\uD83C\uDDEB\uD83C\uDDF7",
                            age = "35 y/o",
                        ),
                    ),
                )
    }

    @Test
    fun `map list with data list model to Content`() {
        every { resources.getString(R.string.userlist_text_fullname_format) } returns "%s %s %s"
        every { resources.getString(R.string.userlist_text_age_format) } returns "%d y/o"

        mapper.map(
            content = UserListViewState.Content(
                isLoading = true,
                userStates = listOf(
                    UserState(
                        uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                        gender = "female",
                        fullname = "Mrs Jane Doe",
                        pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                        flag = "\uD83C\uDDEB\uD83C\uDDF7",
                        age = "35 y/o",
                    ),
                ),
            ),
            users = listOf(
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
            ),
        ) shouldBe
                UserListViewState.Content(
                    isLoading = false,
                    userStates =
                        listOf(
                        UserState(
                            uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                            gender = "female",
                            fullname = "Mrs Jane Doe",
                            pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                            flag = "\uD83C\uDDEB\uD83C\uDDF7",
                            age = "35 y/o",
                        ),
                        UserState(
                            uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                            gender = "female",
                            fullname = "Mrs Jane Doe",
                            pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                            flag = "\uD83C\uDDEB\uD83C\uDDF7",
                            age = "35 y/o",
                        ),
                    ),
                )
    }

    @Test
    fun `map list with data list model to Error`() {
        every { resources.getString(R.string.userlist_text_generic_error) } returns "generic error"

        mapper.map(
            viewState = UserListViewState.Content(),
            exception = Exception(),
        ) shouldBe
                UserListViewState.Error(message = "generic error")
    }
}