package com.uteke.contactbook.features.userdetails.data.model

data class NoUserForIdException(val id: String) : RuntimeException()