package com.uteke.contactbook.features.userdetails.presentation

import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepository
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import java.time.LocalDate
import java.time.Month

internal val fakeGetUserByIdRepositoryReturnsUser = object : GetUserByIdRepository {
    override suspend fun invoke(uuid: String): UserDataModel =
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

internal val fakeGetUserByIdRepositoryThrowsError = object : GetUserByIdRepository {
    override suspend fun invoke(uuid: String): UserDataModel =
        throw NoUserForIdException(id = uuid)
}