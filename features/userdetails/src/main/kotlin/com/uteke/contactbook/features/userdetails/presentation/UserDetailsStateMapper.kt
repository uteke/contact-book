package com.uteke.contactbook.features.userdetails.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.userdetails.R
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.util.Locale

interface UserDetailsStateMapper {
    fun map(user: UserDataModel): UserDetailsViewState.Content
    fun map(exception: Exception): UserDetailsViewState.Error
}

class UserDetailsStateMapperImpl(
    private val resources: Resources,
    private val locale: Locale,
) : UserDetailsStateMapper {

    override fun map(user: UserDataModel): UserDetailsViewState.Content =
        with(user) {
            UserDetailsViewState.Content(
                gender = user.gender,
                nationality = Locale("", user.nationality).getDisplayCountry(locale),
                username = user.username,
                fullname = resources.getString(R.string.userdetails_text_fullname_format)
                    .format(title, firstname, lastname),
                pictureUrl = pictureUrl,
                age = resources.getString(R.string.userdetails_text_age_format)
                    .format(age),
                dateOfBirth = DateTimeFormatter
                    .ofPattern(resources.getString(R.string.userdetails_text_date_format))
                    .format(dateOfBirth),
                address = resources.getString(R.string.userdetails_text_address_format)
                    .format(streetNumber, streetName),
                city = city,
                state = state,
                country = country,
                postcode = postcode,
            )
        }

    override fun map(exception: Exception): UserDetailsViewState.Error =
        UserDetailsViewState.Error(
            message = if (exception is NoUserForIdException) {
                resources.getString(R.string.userdetails_text_no_user_error)
                    .format(exception.id)
            } else {
                resources.getString(R.string.userdetails_text_generic_error)
            }
        )
}