package com.uteke.contactbook.features.userlist.data.model

data class UserNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)