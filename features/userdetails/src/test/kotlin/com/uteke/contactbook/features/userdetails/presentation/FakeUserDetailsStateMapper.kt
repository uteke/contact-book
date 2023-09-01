package com.uteke.contactbook.features.userdetails.presentation

import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.presentation.view.ViewState
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.util.Locale

internal val fakeViewStateMapper = object : ViewStateMapper {
    override fun map(user: UserDataModel): ViewState.Content =
        with(user) {
            ViewState.Content(
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


    override fun map(exception: Exception): ViewState.Error =
        ViewState.Error(message = "error message")
}