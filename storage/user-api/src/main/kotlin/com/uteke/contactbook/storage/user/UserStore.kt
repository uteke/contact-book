package com.uteke.contactbook.storage.user

import com.uteke.contactbook.storage.user.model.UserLocalModel

interface UserStore {
    suspend fun saveUsers(users: List<UserLocalModel>)
    suspend fun getUsersByRange(min: Int, max: Int): List<UserLocalModel>
    suspend fun getUserBy(uuid: String): UserLocalModel?
}
