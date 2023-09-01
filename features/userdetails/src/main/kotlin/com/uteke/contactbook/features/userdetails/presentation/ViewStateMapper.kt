package com.uteke.contactbook.features.userdetails.presentation

import android.content.res.Resources
import com.uteke.contactbook.features.userdetails.R
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.presentation.view.ViewState
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.util.Locale

interface ViewStateMapper {
    fun map(user: UserDataModel): ViewState.Content
    fun map(exception: Exception): ViewState.Error
}

class ViewStateMapperImpl(
    private val resources: Resources,
    private val locale: Locale,
) : ViewStateMapper {

    override fun map(user: UserDataModel): ViewState.Content =
        with(user) {
            ViewState.Content(
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

    override fun map(exception: Exception): ViewState.Error =
        ViewState.Error(
            message = if (exception is NoUserForIdException) {
                resources.getString(R.string.userdetails_text_no_user_error)
                    .format(exception.id)
            } else {
                resources.getString(R.string.userdetails_text_generic_error)
            }
        )
}