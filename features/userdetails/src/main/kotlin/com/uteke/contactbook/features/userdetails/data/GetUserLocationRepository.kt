package com.uteke.contactbook.features.userdetails.data

import com.uteke.contactbook.features.userdetails.data.model.UserLocationDataModel

interface GetUserLocationRepository {
    suspend operator fun invoke(uuid: String): UserLocationDataModel
}