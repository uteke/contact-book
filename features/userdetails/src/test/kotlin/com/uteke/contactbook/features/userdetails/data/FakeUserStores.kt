package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel

internal val fakeUserStoreReturnsUser = object : UserStore {
    override suspend fun saveUsers(users: List<UserLocalModel>) {}

    override suspend fun getUsersByRange(min: Int, max: Int): List<UserLocalModel> = emptyList()

    override suspend fun getUserBy(uuid: String): UserLocalModel =
        UserLocalModel(
            index = 1,
            uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
            username = "yellowbutterfly577",
            gender = "female",
            title =  "Mrs",
            firstname = "Frances",
            lastname = "Herrera",
            thumbnailPictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
            largePictureUrl = "https://randomuser.me/api/portraits/large/women/15.jpg",
            nationality = "FR",
            dateOfBirth = "1983-05-27T11:26:06.318Z",
            age = 40,
            streetNumber = 5740,
            streetName = "W Dallas St",
            city = "Wollongong",
            state =  "Australian Capital Territory",
            country = "Australia",
            postcode = "3018",
        )
}

internal val fakeUserStoreThrowsError = object : UserStore {
    override suspend fun saveUsers(users: List<UserLocalModel>) {}

    override suspend fun getUsersByRange(min: Int, max: Int): List<UserLocalModel> = emptyList()

    override suspend fun getUserBy(uuid: String): UserLocalModel? = null
}
