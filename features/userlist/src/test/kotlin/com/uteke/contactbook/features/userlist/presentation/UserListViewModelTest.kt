package com.uteke.contactbook.features.userlist.presentation

import app.cash.turbine.test
import com.uteke.contactbook.features.common.dispatcher.TestDispatcherProvider
import com.uteke.contactbook.features.userlist.data.GetUserListRepository
import com.uteke.contactbook.features.userlist.presentation.UserListAction
import com.uteke.contactbook.features.userlist.presentation.UserListViewModel
import com.uteke.contactbook.features.userlist.presentation.UserListViewState
import com.uteke.contactbook.features.userlist.presentation.UserState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class UserListViewModelTest {

    @Test
    fun `process Load GIVEN repository returns list user THEN emit state Loading and Content with list`() =
        runTest {
            with(viewModel(getUserListRepository = fakeGetUserListRepositoryReturnsUserList)) {
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true,
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = false,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )
                }
            }
        }

    @Test
    fun `process Load twice GIVEN repository returns list user THEN emit state Loading, Content, Content with loader`() =
        runTest {
            with(viewModel(getUserListRepository = fakeGetUserListRepositoryReturnsUserList)) {
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Load)
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true,
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = false,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = false,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )
                }
            }
        }

    @Test
    fun `process Load and Reload GIVEN repository returns list user THEN emit state Loading, Content, Content with loader`() =
        runTest {
            with(viewModel(getUserListRepository = fakeGetUserListRepositoryReturnsUserList)) {
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Load)
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Reload)

                viewStateFlow.test {
                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = false,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = false,
                        userStates = listOf(
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                            UserState(
                                uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                                gender = "male",
                                fullname = "Mr John Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                                flag = "US",
                                age = "27 ans",
                            ),
                            UserState(
                                uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                                gender = "female",
                                fullname = "Mrs Jane Doe",
                                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                                flag = "FR",
                                age = "35 ans",
                            ),
                        ),
                    )
                }
            }
        }

    @Test
    fun `process Load GIVEN repository returns empty list THEN emit state Loading and Content with empty list`() =
        runTest {
            with(viewModel(getUserListRepository = fakeGetUserListRepositoryReturnsEmptyList)) {
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true,
                    )

                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = false,
                        userStates = emptyList(),
                    )
                }
            }
        }

    @Test
    fun `process Load GIVEN repository throws exception THEN emit state Loading and Error`() =
        runTest {
            with(viewModel(getUserListRepository = fakeGetUserListRepositoryThrowsGetUserListException)) {
                process(com.uteke.contactbook.features.userlist.presentation.UserListAction.Load)

                viewStateFlow.test {
                    awaitItem() shouldBe UserListViewState.Content(
                        isLoading = true,
                    )

                    awaitItem() shouldBe UserListViewState.Error(
                        message = "generic error",
                    )
                }
            }
        }

    private fun TestScope.viewModel(
        getUserListRepository: GetUserListRepository,
    ) = UserListViewModel(
        getUserListRepository = getUserListRepository,
        userListStateMapper = fakeUserListStateMapper,
        dispatcherProvider = TestDispatcherProvider(scheduler = testScheduler),
        connectivityMonitor = fakeConnectivityMonitor(),
    )
}