package com.uteke.contactbook.features.userdetails.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.userdetails.R
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsStateMapperImpl
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewState
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.lang.Exception
import java.time.LocalDate
import java.time.Month
import java.util.Locale

class UserDetailsStateMapperImplTest {
    private val resources = mockk<Resources>()
    private val mapper = UserDetailsStateMapperImpl(resources = resources, locale = Locale.ENGLISH)

    @Test
    fun `map empty list with data list model to Content`() {
        every { resources.getString(R.string.userdetails_text_fullname_format) } returns "%s %s %s"
        every { resources.getString(R.string.userdetails_text_age_format) } returns "%d y/o"
        every { resources.getString(R.string.userdetails_text_date_format) } returns "yyyy/MM/dd"
        every { resources.getString(R.string.userdetails_text_address_format) } returns "%d %s"

        mapper.map(
            user = UserDataModel(
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
            ),
        ) shouldBe
                UserDetailsViewState.Content(
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

    @Test
    fun `map exception to Error`() {
        every { resources.getString(R.string.userdetails_text_generic_error) } returns "generic error"

        mapper.map(exception = Exception()) shouldBe
                UserDetailsViewState.Error(message = "generic error")
    }

    @Test
    fun `map NoUserForIdException to Error`() {
        every { resources.getString(R.string.userdetails_text_no_user_error) } returns "no user %s"

        mapper.map(exception = NoUserForIdException(id = "56989ef1-ee6a")) shouldBe
                UserDetailsViewState.Error(message = "no user 56989ef1-ee6a")
    }
}