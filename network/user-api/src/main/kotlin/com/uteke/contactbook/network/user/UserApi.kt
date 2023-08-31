package com.uteke.contactbook.network.user

import com.uteke.contactbook.network.user.model.GetUsersApiModel

interface UserApi {
    suspend fun getUsers(seed: String, limit: Int, page: Int): Result<GetUsersApiModel>
}
