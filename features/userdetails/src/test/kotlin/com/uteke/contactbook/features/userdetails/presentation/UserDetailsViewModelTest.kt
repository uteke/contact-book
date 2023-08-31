package com.uteke.contactbook.features.userdetails.presentation

import app.cash.turbine.test
import com.uteke.contactbook.features.common.dispatcher.TestDispatcherProvider
import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepository
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsAction
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewModel
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class UserDetailsViewModelTest {

    @Test
    fun `process Load GIVEN repository returns data THEN update state Content`() =
        runTest {
            with(viewModel(getUserByIdRepository = fakeGetUserByIdRepositoryReturnsUser)) {
                process(com.uteke.contactbook.features.userdetails.presentation.UserDetailsAction.Load(id = "56989ef1-ee6a-4d6b-a20f-18b343213437"))

                viewStateFlow.test {
                    awaitItem() shouldBe UserDetailsViewState.Loading

                    awaitItem() shouldBe UserDetailsViewState.Content(
                        pictureUrl = "https://randomuser.me/api/portraits/large/women/15.jpg",
                        username = "yellowbutterfly577",
                        gender = "female",
                        fullname = "Mrs Frances Herrera",
                        nationality = "France",
                        dateOfBirth = "1983/05/27",
                        age = "40 y/o",
                        address = "5740 W Dallas St",
                        city = "Wollongong",
                        state =  "Australian Capital Territory",
                        country = "Australia",
                        postcode = "3018",
                    )
                }
            }
        }

    @Test
    fun `process Load GIVEN repository throws error THEN update state Error`() =
        runTest {
            with(viewModel(getUserByIdRepository = fakeGetUserByIdRepositoryThrowsError)) {
                process(com.uteke.contactbook.features.userdetails.presentation.UserDetailsAction.Load(id = "56989ef1-ee6a-4d6b-a20f-18b343213437"))

                viewStateFlow.test {
                    awaitItem() shouldBe UserDetailsViewState.Loading

                    awaitItem() shouldBe UserDetailsViewState.Error(
                        message = "error message",
                    )
                }
            }
        }

    private fun TestScope.viewModel(getUserByIdRepository: GetUserByIdRepository) =
        UserDetailsViewModel(
            getUserByIdRepository = getUserByIdRepository,
            userDetailsStateMapper = fakeUserDetailsStateMapper,
            dispatcherProvider = TestDispatcherProvider(testScheduler),
        )
}