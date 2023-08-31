package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import com.uteke.contactbook.features.userlist.data.model.GetUserListException
import com.uteke.contactbook.features.userlist.data.model.PagedUserListDataModel
import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import com.uteke.contactbook.network.user.UserApi
import com.uteke.contactbook.network.user.model.UserApiModel
import com.uteke.contactbook.storage.user.UserStore
import com.uteke.contactbook.storage.user.model.UserLocalModel
import kotlinx.coroutines.withContext

class GetUserListRepositoryImpl(
    private val userApi: UserApi,
    private val userStore: UserStore,
    private val dispatcher: DispatcherProvider,
) : GetUserListRepository {
    override suspend fun invoke(page: Int, limit: Int): PagedUserListDataModel =
        withContext(dispatcher.io) {
            fetchAndSave(page = page, limit = limit)
            retrieve(page = page, limit = limit)
        }

    private suspend fun fetchAndSave(page: Int, limit: Int) =
        try {
            val minIndex = limit * (page - 1)

            userApi.getUsers(seed = "random", limit = limit, page = page)
                .getOrNull()
                ?.also {
                    userStore.saveUsers(
                        users = it.results.mapIndexed { index, userApiModel ->
                            userApiModel.toUserLocalModel(
                                index = minIndex + index
                            )
                        }
                    )
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    private suspend fun retrieve(page: Int, limit: Int) =
        try {
            val minIndex = limit * (page - 1)

            userStore.getUsersByRange(
                min = minIndex,
                max = limit * page - 1,
            )
                .sortedBy { it.index }
                .map { it.toUserDataModel() }
                .let {
                    PagedUserListDataModel(
                        page = page,
                        users = it,
                    )
                }
        } catch (exception: Exception) {
            throw GetUserListException(
                message = "no users for given page $page",
                cause = exception,
            )
        }

    private fun UserLocalModel.toUserDataModel() =
        UserDataModel(
            uuid = uuid,
            gender = gender,
            title = title,
            firstname = firstname,
            lastname = lastname,
            age = age,
            pictureUrl = thumbnailPictureUrl,
            nationality = nationality,
        )

    private fun UserApiModel.toUserLocalModel(index: Int) =
        UserLocalModel(
            index = index,
            uuid = login.uuid,
            username = login.username,
            gender = gender,
            title = name.title,
            firstname = name.first,
            lastname = name.last,
            age = dateOfBirth.age,
            dateOfBirth = dateOfBirth.date,
            thumbnailPictureUrl = picture.thumbnail,
            largePictureUrl = picture.large,
            nationality = nationality,
            streetNumber = location.street.number,
            streetName = location.street.name,
            city = location.city,
            state = location.state,
            country = location.country,
            postcode = location.postcode,
        )
}