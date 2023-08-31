package com.uteke.contactbook.network.user.model

data class UserApiException(
    override val cause: Throwable? = null,
) : Throwable(cause)