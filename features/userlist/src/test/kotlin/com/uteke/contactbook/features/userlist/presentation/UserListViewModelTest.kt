package com.uteke.contactbook.features.userlist.presentation

import app.cash.turbine.test
import com.uteke.contactbook.features.common.dispatcher.TestDispatcherProvider
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.model.Event
import com.uteke.contactbook.features.userlist.presentation.view.ViewState
import com.uteke.contactbook.features.userlist.presentation.view.UserState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class UserListViewModelTest {
    @Test
    fun `process CheckConnectivity GIVEN processor process action and mutate THEN emit state`() =
        runTest {
            with(viewModel(isConnectionLost = true)) {
                process(Action.CheckConnectivity)

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState(isConnectivityVisible = false)
                    awaitItem() shouldBe ViewState(isConnectivityVisible = true)
                }

                eventFlow.test { expectNoEvents() }
            }
        }

    @Test
    fun `process Load GIVEN processor process action and mutate THEN emit state with list`() =
        runTest {
            with(viewModel()) {
                process(Action.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState()
                    awaitItem() shouldBe ViewState(isLoaderVisible = true)
                    awaitItem() shouldBe ViewState(
                        isLoaderVisible = false,
                        isUserListVisible = true,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 y/o",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 y/o",
                            ),
                        ),
                    )
                }

                eventFlow.test { expectNoEvents() }
            }
        }

    @Test
    fun `process Load twice GIVEN processor process action and mutate THEN emit state with list`() =
        runTest {
            with(viewModel()) {
                process(Action.Load)
                process(Action.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState()
                    awaitItem() shouldBe ViewState(isLoaderVisible = true)
                    awaitItem() shouldBe ViewState(
                        isLoaderVisible = false,
                        isUserListVisible = true,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 y/o",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 y/o",
                            ),
                        ),
                    )
                    awaitItem() shouldBe ViewState(
                        isLoaderVisible = true,
                        isUserListVisible = true,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 y/o",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 y/o",
                            ),
                        ),
                    )
                    awaitItem() shouldBe ViewState(
                        isLoaderVisible = false,
                        isUserListVisible = true,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 y/o",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 y/o",
                            ),
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 y/o",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 y/o",
                            ),
                        ),
                    )
                }

                eventFlow.test { expectNoEvents() }
            }
        }

    @Test
    fun `process Load GIVEN processor process action and mutate THEN emit state with empty list`() =
        runTest {
            with(viewModel(users = emptyList())) {
                process(Action.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState()
                    awaitItem() shouldBe ViewState(isLoaderVisible = true)
                    awaitItem() shouldBe ViewState(
                        isLoaderVisible = false,
                        isUserListVisible = true,
                        userStates = emptyList(),
                    )
                }

                eventFlow.test { expectNoEvents() }
            }
        }

    @Test
    fun `process Load GIVEN processor process action and mutate THEN emit state with error`() =
        runTest {
            with(viewModel(users = null)) {
                process(Action.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState()
                    awaitItem() shouldBe ViewState(isLoaderVisible = true)
                    awaitItem() shouldBe ViewState(
                        isLoaderVisible = false,
                        isUserListVisible = false,
                        isErrorVisible = true,
                        errorMessage = "generic error by error message"
                    )
                }

                eventFlow.test { expectNoEvents() }
            }
        }

    @Test
    fun `process EmailClick GIVEN processor process action and mutate THEN emit OpenEmail`() =
        runTest {
            with(viewModel(users = null)) {
                process(Action.EmailClick(uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437"))

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState()
                }

                eventFlow.test {
                    awaitItem() shouldBe Event.OpenEmail(email = "john.doe@example.com")
                }
            }
        }

    @Test
    fun `process PhoneClick GIVEN processor process action and mutate THEN emit OpenPhone`() =
        runTest {
            with(viewModel(users = null)) {
                process(Action.PhoneClick(uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437"))

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState()
                }

                eventFlow.test {
                    awaitItem() shouldBe Event.OpenPhone(phone = "0123456789")
                }
            }
        }

    private fun TestScope.viewModel(
        users: List<UserDataModel>? = fakeUsers,
        isConnectionLost: Boolean = false,
    ) = UserListViewModel(
        reducers = listOf(fakeReducer),
        actionProcessors = listOf(
            fakeActionProcessor(isConnectionLost = isConnectionLost, users = users),
        ),
        dispatcherProvider = TestDispatcherProvider(testScheduler),
    )
}