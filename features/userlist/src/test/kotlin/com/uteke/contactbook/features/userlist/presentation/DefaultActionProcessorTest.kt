package com.uteke.contactbook.features.userlist.presentation

import app.cash.turbine.test
import com.uteke.contactbook.features.common.monitor.ConnectivityStatus
import com.uteke.contactbook.features.userlist.data.GetUserListRepository
import com.uteke.contactbook.features.userlist.data.model.GetUserListException
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DefaultActionProcessorTest {
    @ParameterizedTest
    @MethodSource("loadActions")
    fun `invoke Load GIVEN repository returns list user THEN emit ShowLoader & ShowContent`(
        action: Action,
    ) =
        runTest {
            with(actionProcessor(getUserListRepository = fakeGetUserListRepositoryReturnsUserList)) {
                invoke(action).test {
                    awaitItem() shouldBe Mutation.ShowLoader.noEvent()
                    awaitItem() shouldBe Mutation.ShowContent(
                        users = listOf(
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
                        ),
                    ).noEvent()
                    awaitComplete()
                }
            }
        }

    @ParameterizedTest
    @MethodSource("loadActions")
    fun `process Load GIVEN repository returns empty list THEN emit state Loading and Content with empty list`(
        action: Action,
    ) =
        runTest {
            with(actionProcessor(getUserListRepository = fakeGetUserListRepositoryReturnsEmptyList)) {
                invoke(action).test {
                    awaitItem() shouldBe Mutation.ShowLoader.noEvent()
                    awaitItem() shouldBe Mutation.ShowContent(users = emptyList()).noEvent()
                    awaitComplete()
                }
            }
        }

    @ParameterizedTest
    @MethodSource("loadActions")
    fun `process Load GIVEN repository throws exception THEN emit state Loading and Error`(
        action: Action,
    ) =
        runTest {
            with(actionProcessor(getUserListRepository = fakeGetUserListRepositoryThrowsGetUserListException)) {
                invoke(action).test {
                    awaitItem() shouldBe Mutation.ShowLoader.noEvent()
                    awaitItem() shouldBe Mutation.ShowError(
                        exception = GetUserListException(message = "error message")
                    ).noEvent()
                    awaitComplete()
                }
            }
        }

    @ParameterizedTest
    @MethodSource("connectivityStatusToMutation")
    fun `process CheckConnectivity GIVEN repository throws exception THEN emit state Loading and Error`(
        connectivityStatus: ConnectivityStatus,
        mutation: Mutation,
    ) =
        runTest {
            with(actionProcessor(connectivityStatus = connectivityStatus)) {
                invoke(Action.CheckConnectivity).test {
                    awaitItem() shouldBe mutation.noEvent()
                    awaitComplete()
                }
            }
        }

    private fun Mutation.noEvent() = this to null

    private fun actionProcessor(
        getUserListRepository: GetUserListRepository = fakeGetUserListRepositoryReturnsUserList,
        connectivityStatus: ConnectivityStatus = ConnectivityStatus.AVAILABLE,
    ) =
        DefaultActionProcessor(
            getUserListRepository = getUserListRepository,
            connectivityMonitor = fakeConnectivityMonitor(connectivityStatus),
        )

    private companion object {
        @JvmStatic
        fun loadActions() = listOf(
            Arguments.of(Action.Load),
            Arguments.of(Action.Reload),
        )

        @JvmStatic
        fun connectivityStatusToMutation() = listOf(
            Arguments.of(ConnectivityStatus.AVAILABLE, Mutation.DismissLostConnection),
            Arguments.of(ConnectivityStatus.UNAVAILABLE, Mutation.ShowLostConnection),
            Arguments.of(ConnectivityStatus.LOST, Mutation.ShowLostConnection),
        )
    }
}