package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.features.common.dispatcher.TestDispatcherProvider
import com.uteke.contactbook.features.userlist.data.GetUserListRepositoryImpl
import com.uteke.contactbook.features.userlist.data.model.GetUserListException
import com.uteke.contactbook.features.userlist.data.model.PagedUserListDataModel
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.network.user.UserApi
import com.uteke.contactbook.storage.user.UserStore
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class GetUserListRepositoryImplTest {
    @Test
    fun `invoke GIVEN api returns success of list of UserApiModel THEN return list of UserDataModel`() =
        runTest {
            val repository = repository(userApi = fakeUserApiReturnsSuccessOfListOfUser)
            val expected = PagedUserListDataModel(
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
                page = 1,
            )

            repository.invoke(page = 1, limit = 10) shouldBe expected
        }

    @Test
    fun `invoke GIVEN api returns success of empty list THEN return empty list of UserDataModel`() =
        runTest {
            val repository = repository(userApi = fakeUserApiReturnsSuccessOfEmptyList)
            val expected = PagedUserListDataModel(users = emptyList(), page = 1)
            repository.invoke(page = 1, limit = 10) shouldBe expected
        }

    @Test
    fun `invoke GIVEN api returns success AND store throws error THEN throw error`() =
        runTest {
            val repository = repository(userStore = fakeUserStoreThrowsAtGet,)
            val throwable = shouldThrow<GetUserListException> {
                repository.invoke(page = 1, limit = 10)
            }
            throwable.message shouldBe "no users for given page 1"
        }

    @Test
    fun `invoke GIVEN api returns failure of Throwable AND store throws error THEN throw error`() =
        runTest {
            val repository = repository(
                userApi = fakeUserApiReturnsFailureOfThrowable,
                userStore = fakeUserStoreThrowsAtGet,
            )
            val throwable = shouldThrow<GetUserListException> {
                repository.invoke(page = 1, limit = 10)
            }
            throwable.message shouldBe "no users for given page 1"
        }

    private fun TestScope.repository(
        userApi: UserApi = fakeUserApiReturnsSuccessOfListOfUser,
        userStore: UserStore = fakeUserStore,
    ) =
        GetUserListRepositoryImpl(
            userApi = userApi,
            userStore = userStore,
            dispatcher = TestDispatcherProvider(testScheduler)
        )
}