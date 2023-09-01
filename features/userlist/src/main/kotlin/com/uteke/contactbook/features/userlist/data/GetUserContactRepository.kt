package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.features.userlist.data.model.UserContactDataModel

interface GetUserContactRepository {
    suspend operator fun invoke(uuid: String): UserContactDataModel
}