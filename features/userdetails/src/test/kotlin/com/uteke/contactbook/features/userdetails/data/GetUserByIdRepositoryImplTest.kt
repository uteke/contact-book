package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepositoryImpl
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.storage.user.UserStore
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

class GetUserByIdRepositoryImplTest {

    @Test
    fun `invoke GIVEN store returns local model THEN returns user data`() = runTest {
        repository(userStore = fakeUserStoreReturnsUser)
            .invoke(uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437") shouldBe
                UserDataModel(
                    uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                    username = "yellowbutterfly577",
                    gender = "female",
                    title =  "Mrs",
                    firstname = "Frances",
                    lastname = "Herrera",
                    pictureUrl = "https://randomuser.me/api/portraits/large/women/15.jpg",
                    nationality = "FR",
                    dateOfBirth = LocalDate.of(1983, Month.MAY, 27),
                    age = 40,
                    streetNumber = 5740,
                    streetName = "W Dallas St",
                    city = "Wollongong",
                    state =  "Australian Capital Territory",
                    country = "Australia",
                    postcode = "3018",
                )
    }

    @Test
    fun `invoke GIVEN store returns null THEN throws error`() = runTest {
        val exception = shouldThrow<NoUserForIdException> {
            repository(userStore = fakeUserStoreThrowsError)
                .invoke(uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437")
        }
        exception.id shouldBe "56989ef1-ee6a-4d6b-a20f-18b343213437"
    }

    private fun repository(userStore: UserStore) =
        GetUserByIdRepositoryImpl(userStore = userStore)
}