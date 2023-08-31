package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetUserByIdRepositoryImpl(
    private val userStore: UserStore,
) : GetUserByIdRepository {
    override suspend fun invoke(uuid: String): UserDataModel =
        userStore.getUserBy(uuid)?.toUserDataModel()
            ?: throw NoUserForIdException(id = uuid)

    private fun UserLocalModel.toUserDataModel() =
        UserDataModel(
            uuid = uuid,
            username = username,
            gender = gender,
            title = title,
            firstname = firstname,
            lastname = lastname,
            nationality = nationality,
            age = age,
            dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            pictureUrl = largePictureUrl,
            streetNumber = streetNumber,
            streetName = streetName,
            city = city,
            state = state,
            country = country,
            postcode = postcode,
        )
}

