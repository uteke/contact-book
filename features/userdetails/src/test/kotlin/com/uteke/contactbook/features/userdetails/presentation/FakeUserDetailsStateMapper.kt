package com.uteke.contactbook.features.userdetails.presentation

import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsStateMapper
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewState
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.util.Locale

internal val fakeUserDetailsStateMapper = object : UserDetailsStateMapper {
    override fun map(user: UserDataModel): UserDetailsViewState.Content =
        with(user) {
            UserDetailsViewState.Content(
                pictureUrl = pictureUrl,
                username = username,
                gender = gender,
                fullname = "$title $firstname $lastname",
                nationality = Locale("", nationality).getDisplayCountry(Locale.ENGLISH),
                dateOfBirth = DateTimeFormatter
                    .ofPattern("yyyy/MM/dd")
                    .format(dateOfBirth),
                age = "$age y/o",
                address = "$streetNumber $streetName",
                city = city,
                state = state,
                country = country,
                postcode = postcode,
            )
        }


    override fun map(exception: Exception): UserDetailsViewState.Error =
        UserDetailsViewState.Error(message = "error message")
}