package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.UserDataModel

interface GetUserByIdRepository {
    suspend operator fun invoke(uuid: String): UserDataModel
}



