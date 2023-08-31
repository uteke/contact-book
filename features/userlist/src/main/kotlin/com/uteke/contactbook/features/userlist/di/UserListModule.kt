package com.uteke.contactbook.features.userlist.di

import com.uteke.contactbook.features.common.monitor.ConnectivityMonitorImpl
import com.uteke.contactbook.features.userlist.data.GetUserListRepositoryImpl
import com.uteke.contactbook.features.userlist.presentation.UserListViewModel
import com.uteke.contactbook.features.userlist.presentation.UserListStateMapperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userListModule = module {
    viewModel {
        UserListViewModel(
            getUserListRepository = GetUserListRepositoryImpl(
                userApi = get(),
                userStore = get(),
                dispatcher = get(),
            ),
            userListStateMapper = UserListStateMapperImpl(
                resources = androidContext().resources,
            ),
            dispatcherProvider = get(),
            connectivityMonitor = ConnectivityMonitorImpl(
                context = androidContext(),
            )
        )
    }
}