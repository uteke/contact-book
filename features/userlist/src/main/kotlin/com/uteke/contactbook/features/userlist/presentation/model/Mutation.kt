package com.uteke.contactbook.features.userlist.presentation.model

import com.uteke.contactbook.features.userlist.data.model.UserDataModel
import java.lang.Exception

sealed interface Mutation {
    data object ShowLostConnection : Mutation
    data object DismissLostConnection : Mutation
    data object ShowLoader : Mutation
    data class ShowContent(val users: List<UserDataModel>) : Mutation
    data class ShowError(val exception: Exception) : Mutation

}