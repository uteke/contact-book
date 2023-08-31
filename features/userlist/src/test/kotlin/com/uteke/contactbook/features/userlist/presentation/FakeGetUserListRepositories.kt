package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.userlist.data.GetUserListRepository
import com.uteke.contactbook.features.userlist.data.model.GetUserListException
import com.uteke.contactbook.features.userlist.data.model.PagedUserListDataModel
import com.uteke.contactbook.features.userlist.data.model.UserDataModel

internal val fakeGetUserListRepositoryReturnsUserList = object : GetUserListRepository {
    override suspend fun invoke(page: Int, limit: Int): PagedUserListDataModel =
        PagedUserListDataModel(
            users = listOf(
                UserDataModel(
                    uuid = "56989ef1-ee6a-4d6b-a20f-18b343213437",
                    gender = "male",
                    title = "Mr",
                    firstname = "John",
                    lastname = "Doe",
                    pictureUrl = "https://randomuser.me/api/portraits/thumb/men/2.jpg",
                    nationality = "US",
                    age = 27,
                ),
                UserDataModel(
                    uuid = "7745ef46-2c62-4e9e-b241-29400da064bc",
                    gender = "female",
                    title = "Mrs",
                    firstname = "Jane",
                    lastname = "Doe",
                    pictureUrl = "https://randomuser.me/api/portraits/thumb/women/15.jpg",
                    nationality = "FR",
                    age = 35,
                ),
            ),
            page = page,
        )
}

internal val fakeGetUserListRepositoryReturnsEmptyList = object : GetUserListRepository {
    override suspend fun invoke(page: Int, limit: Int): PagedUserListDataModel =
        PagedUserListDataModel(
            users = emptyList(),
            page = page,
        )
}

internal val fakeGetUserListRepositoryThrowsGetUserListException = object : GetUserListRepository {
    override suspend fun invoke(page: Int, limit: Int): PagedUserListDataModel =
        throw GetUserListException(message = "error message")
}