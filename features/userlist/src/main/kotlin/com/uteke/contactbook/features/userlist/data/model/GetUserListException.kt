package com.uteke.contactbook.features.userlist.data.model

data class GetUserListException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)