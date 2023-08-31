package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel

internal val fakeUserStore = object : UserStore {
    val cache = mutableListOf<UserLocalModel>()

    override suspend fun saveUsers(users: List<UserLocalModel>) {
        cache.apply {
            users.forEach { user ->
                getOrNull(user.index)
                    ?.run { set(user.index, user) }
                    ?: add(user.index, user)
            }
        }
    }

    override suspend fun getUsersByRange(min: Int, max: Int): List<UserLocalModel> =
        cache.filter { it.index in min..max }

    override suspend fun getUserBy(uuid: String): UserLocalModel? =
        cache.firstOrNull { it.uuid == uuid }
}

internal val fakeUserStoreThrowsAtGet = object : UserStore {
    val cache = mutableListOf<UserLocalModel>()

    override suspend fun saveUsers(users: List<UserLocalModel>) {
        cache.apply {
            users.forEach { user ->
                getOrNull(user.index)
                    ?.run { set(user.index, user) }
                    ?: add(user.index, user)
            }
        }
    }

    override suspend fun getUsersByRange(min: Int, max: Int): List<UserLocalModel> =
        throw Exception("error at get users")

    override suspend fun getUserBy(uuid: String): UserLocalModel? =
        cache.firstOrNull { it.uuid == uuid }
}
