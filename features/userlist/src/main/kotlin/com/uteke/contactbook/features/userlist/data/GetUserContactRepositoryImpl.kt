package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.features.userlist.data.model.UserContactDataModel
import com.uteke.contactbook.features.userlist.data.model.UserNotFoundException
import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel

class GetUserContactRepositoryImpl(
    private val userStore: UserStore,
) : GetUserContactRepository {
    override suspend fun invoke(uuid: String): UserContactDataModel =
        userStore.getUserBy(uuid = uuid)
            ?.toUserContactDataModel()
            ?: throw UserNotFoundException()

    private fun UserLocalModel.toUserContactDataModel() =
        UserContactDataModel(
            uuid = uuid,
            email = email,
            cell = cell,
            phone = phone,
        )
}