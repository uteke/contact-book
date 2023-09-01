package com.uteke.contactbook.features.userlist.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.userlist.R
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import com.uteke.contactbook.features.userlist.presentation.view.UserState
import com.uteke.contactbook.features.userlist.presentation.view.ViewState
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.lang.Exception

class DefaultReducerTest {
    private val resources: Resources = mockk()
    private val reducer = DefaultReducer(resources)

    @Test
    fun `invoke ShowLostConnection returns new state`() {
        reducer.invoke(Mutation.ShowLostConnection, ViewState()) shouldBe
                ViewState(isConnectivityVisible = true)
    }

    @Test
    fun `invoke DismissLostConnection returns new state`() {
        reducer.invoke(Mutation.DismissLostConnection, ViewState()) shouldBe
                ViewState(isConnectivityVisible = false)
    }

    @Test
    fun `invoke isLoaderVisible returns new state`() {
        reducer.invoke(Mutation.ShowLoader, ViewState()) shouldBe
                ViewState(isLoaderVisible = true)
    }

    @Test
    fun `invoke ShowContent with current list returns new state`() {
        every { resources.getString(R.string.userlist_text_fullname_format) } returns "%s %s %s"
        every { resources.getString(R.string.userlist_text_age_format) } returns "%d y/o"

        reducer.invoke(
            Mutation.ShowContent(
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
            ),
            ViewState(
                isLoaderVisible = true,
                isUserListVisible = true,
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
        ) shouldBe
                ViewState(
                    isLoaderVisible = false,
                    isUserListVisible = true,
                    userStates = listOf(
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
    fun `invoke ShowContent with empty list returns new state`() {
        every { resources.getString(R.string.userlist_text_fullname_format) } returns "%s %s %s"
        every { resources.getString(R.string.userlist_text_age_format) } returns "%d y/o"

        reducer.invoke(
            Mutation.ShowContent(
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
            ),
            ViewState(
                isLoaderVisible = true,
                isUserListVisible = false,
                userStates = emptyList(),
            )
        ) shouldBe
                ViewState(
                    isLoaderVisible = false,
                    isUserListVisible = true,
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
    fun `invoke ShowError returns new state`() {
        every { resources.getString(R.string.userlist_text_generic_error) } returns
                "generic error by %s"

        reducer.invoke(
            Mutation.ShowError(exception = Exception("unknown cause")),
            ViewState(
                isLoaderVisible = true,
                isErrorVisible = false,
                isUserListVisible = true,
            )
        ) shouldBe
                ViewState(
                    isLoaderVisible = false,
                    isErrorVisible = true,
                    isUserListVisible = false,
                    errorMessage = "generic error by unknown cause",
                )
    }
}