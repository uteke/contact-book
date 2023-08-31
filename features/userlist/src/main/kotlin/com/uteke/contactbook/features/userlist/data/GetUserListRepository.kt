package com.uteke.contactbook.features.userlist.data

import com.uteke.contactbook.features.userlist.data.model.PagedUserListDataModel

interface GetUserListRepository {
    suspend operator fun invoke(page: Int, limit: Int): PagedUserListDataModel
}

