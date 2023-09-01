package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.NoUserForIdException
import com.uteke.contactbook.features.userdetails.data.model.UserContactDataModel
import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel

class GetUserContactRepositoryImpl(
    private val userStore: UserStore,
) : GetUserContactRepository {
    override suspend fun invoke(uuid: String): UserContactDataModel =
        userStore.getUserBy(uuid = uuid)
            ?.toUserContactDataModel()
            ?: throw NoUserForIdException(id = uuid)

    private fun UserLocalModel.toUserContactDataModel() =
        UserContactDataModel(
            uuid = uuid,
            email = email,
            cell = cell,
            phone = phone,
        )
}