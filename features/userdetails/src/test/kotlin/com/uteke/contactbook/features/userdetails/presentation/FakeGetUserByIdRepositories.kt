package com.uteke.contactbook.features.userdetails.presentation

import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepository
import com.uteke.contactbook.features.userdetails.data.GetUserContactRepository
import com.uteke.contactbook.features.userdetails.data.GetUserLocationRepository
import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserContactDataModel
import com.uteke.contactbook.features.userdetails.data.model.UserDataModel
import com.uteke.contactbook.features.userdetails.data.model.UserLocationDataModel
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

internal val fakeGetUserContactRepositoryReturnsContact = object : GetUserContactRepository {
    override suspend fun invoke(uuid: String): UserContactDataModel =
        UserContactDataModel(
            uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
            email = "frances.herrera@example.com",
            phone = "02-1122-1470",
            cell = "0444-339-924",
        )
}

internal val fakeGetUserContactRepositoryThrowsError = object : GetUserContactRepository {
    override suspend fun invoke(uuid: String): UserContactDataModel =
        throw NoUserForIdException(id = uuid)
}

internal val fakeGetUserLocationRepositoryReturnsLocation = object : GetUserLocationRepository {
    override suspend fun invoke(uuid: String): UserLocationDataModel =
        UserLocationDataModel(
            uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
            latitude = -69.8246,
            longitude = 134.8719,
        )
}

internal val fakeGetUserLocationRepositoryThrowsError = object : GetUserLocationRepository {
    override suspend fun invoke(uuid: String): UserLocationDataModel =
        throw NoUserForIdException(id = uuid)
}