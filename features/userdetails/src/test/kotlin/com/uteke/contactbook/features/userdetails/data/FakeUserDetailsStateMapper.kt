package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsStateMapper
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewState
import java.lang.Exception
import java.time.format.DateTimeFormatter

internal val fakeMapper = object : UserDetailsStateMapper {
    override fun map(user: UserDataModel): UserDetailsViewState.Content =
        with(user) {
            UserDetailsViewState.Content(
                gender = user.gender,
                username = user.username,
                fullname = "$title $firstname $lastname",
                pictureUrl = pictureUrl,
                age = "$age y/o",
                dateOfBirth = DateTimeFormatter
                    .ofPattern("EEEE dd MMM")
                    .format(dateOfBirth),
                address = "$streetNumber $streetName",
                city = city,
                state = state,
                country = country,
                postcode = postcode,
                nationality = nationality,
            )
        }

    override fun map(exception: Exception): UserDetailsViewState.Error =
        UserDetailsViewState.Error(
            message = "error message"
        )
}