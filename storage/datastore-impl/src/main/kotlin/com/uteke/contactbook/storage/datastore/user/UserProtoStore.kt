package com.uteke.contactbook.storage.datastore.user

import androidx.datastore.core.DataStore
import com.uteke.contactbook.storage.datastore.user.UserProto
import com.uteke.contactbook.storage.datastore.user.UsersProto
import com.uteke.contactbook.storage.datastore.user.copy
import com.uteke.contactbook.storage.datastore.user.userProto
import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel
import kotlinx.coroutines.flow.firstOrNull

class UserProtoStore(
    private val dataStore: DataStore<UsersProto>,
) : UserStore {
    override suspend fun saveUsers(users: List<UserLocalModel>) {
        dataStore.updateData { data ->
            data.copy {
                users.forEach { user ->
                    if (user.index < this.users.size) {
                        this.users[user.index] = user.newBuilder()
                    } else {
                        this.users.plusAssign(user.newBuilder())
                    }
                }
            }
        }
    }

    override suspend fun getUsersByRange(min: Int, max: Int): List<UserLocalModel> =
        dataStore.data
            .firstOrNull()
            ?.usersList
            ?.filter { it.index in min..max }
            ?.map { it.toUserLocalModel() }
            ?: emptyList()

    override suspend fun getUserBy(uuid: String): UserLocalModel? =
        dataStore.data
            .firstOrNull()
            ?.usersList
            ?.firstOrNull { it.uuid == uuid }
            ?.toUserLocalModel()

    private fun UserLocalModel.newBuilder() =
        userProto {
            index = this@newBuilder.index
            uuid = this@newBuilder.uuid
            username = this@newBuilder.username
            gender = this@newBuilder.gender
            title = this@newBuilder.title
            firstname = this@newBuilder.firstname
            lastname = this@newBuilder.lastname
            age = this@newBuilder.age
            dateOfBirth = this@newBuilder.dateOfBirth
            thumbnailPictureUrl = this@newBuilder.thumbnailPictureUrl
            largePictureUrl = this@newBuilder.largePictureUrl
            nationality = this@newBuilder.nationality
            streetNumber = this@newBuilder.streetNumber
            streetName = this@newBuilder.streetName
            city = this@newBuilder.city
            state = this@newBuilder.state
            country = this@newBuilder.country
            postcode = this@newBuilder.postcode
        }

    private fun UserProto.toUserLocalModel() =
        UserLocalModel(
            index = index,
            uuid = uuid,
            username = username,
            gender = gender,
            title = title,
            firstname = firstname,
            lastname = lastname,
            age = age,
            dateOfBirth = dateOfBirth,
            thumbnailPictureUrl = thumbnailPictureUrl,
            largePictureUrl = largePictureUrl,
            nationality = nationality,
            streetNumber = streetNumber,
            streetName = streetName,
            city = city,
            state = state,
            country = country,
            postcode = postcode,
        )
}
