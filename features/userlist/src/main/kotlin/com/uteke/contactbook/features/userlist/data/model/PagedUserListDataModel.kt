package com.uteke.contactbook.features.userlist.data.model

data class PagedUserListDataModel(
    val users: List<UserDataModel>,
    val page: Int,
)
