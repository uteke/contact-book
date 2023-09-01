package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.UserContactDataModel

interface GetUserContactRepository {
    suspend operator fun invoke(uuid: String): UserContactDataModel
}