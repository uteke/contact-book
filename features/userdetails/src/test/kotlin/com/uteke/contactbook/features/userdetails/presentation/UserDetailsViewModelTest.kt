package com.uteke.contactbook.features.userdetails.presentation

import app.cash.turbine.test
import com.uteke.contactbook.features.common.dispatcher.TestDispatcherProvider
import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepository
import com.uteke.contactbook.features.userdetails.presentation.model.Action
import com.uteke.contactbook.features.userdetails.presentation.view.ViewState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class UserDetailsViewModelTest {

    @Test
    fun `process Load GIVEN repository returns data THEN update state Content`() =
        runTest {
            with(viewModel(getUserByIdRepository = fakeGetUserByIdRepositoryReturnsUser)) {
                process(Action.Load(id = "56989ef1-ee6a-4d6b-a20f-18b343213437"))

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState.Loading

                    awaitItem() shouldBe ViewState.Content(
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
                process(Action.Load(id = "56989ef1-ee6a-4d6b-a20f-18b343213437"))

                viewStateFlow.test {
                    awaitItem() shouldBe ViewState.Loading

                    awaitItem() shouldBe ViewState.Error(
                        message = "error message",
                    )
                }
            }
        }

    private fun TestScope.viewModel(getUserByIdRepository: GetUserByIdRepository) =
        UserDetailsViewModel(
            getUserByIdRepository = getUserByIdRepository,
            getUserContactRepository = fakeGetUserContactRepositoryReturnsContact,
            getUserLocationRepository = fakeGetUserLocationRepositoryReturnsLocation,
            viewStateMapper = fakeViewStateMapper,
            dispatcherProvider = TestDispatcherProvider(testScheduler),
        )
}