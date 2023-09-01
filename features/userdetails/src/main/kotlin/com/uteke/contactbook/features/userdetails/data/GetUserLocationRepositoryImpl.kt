package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserLocationDataModel
import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel

class GetUserLocationRepositoryImpl(
    private val userStore: UserStore,
) : GetUserLocationRepository {
    override suspend fun invoke(uuid: String): UserLocationDataModel =
        userStore.getUserBy(uuid = uuid)
            ?.toUserLocationDataModel()
            ?: throw NoUserForIdException(id = uuid)

    private fun UserLocalModel.toUserLocationDataModel() =
        UserLocationDataModel(
            uuid = uuid,
            latitude = latitude,
            longitude = longitude,
        )
}