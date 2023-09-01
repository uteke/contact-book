package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.presentation.ViewStateMapper
import com.uteke.contactbook.features.userdetails.presentation.view.ViewState
import java.lang.Exception
import java.time.format.DateTimeFormatter

internal val fakeMapper = object : ViewStateMapper {
    override fun map(user: UserDataModel): ViewState.Content =
        with(user) {
            ViewState.Content(
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

    override fun map(exception: Exception): ViewState.Error =
        ViewState.Error(
            message = "error message"
        )
}