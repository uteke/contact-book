package com.uteke.contactbook.features.userlist.di

import com.uteke.contactbook.features.common.monitor.ConnectivityMonitorImpl
import com.uteke.contactbook.features.userlist.data.GetUserContactRepositoryImpl
import com.uteke.contactbook.features.userlist.data.GetUserListRepositoryImpl
import com.uteke.contactbook.features.userlist.presentation.DefaultActionProcessor
import com.uteke.contactbook.features.userlist.presentation.DefaultReducer
import com.uteke.contactbook.features.userlist.presentation.UserActionProcessor
import com.uteke.contactbook.features.userlist.presentation.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userListModule = module {
    viewModel {
        UserListViewModel(
            actionProcessors = listOf(
                DefaultActionProcessor(
                    getUserListRepository = GetUserListRepositoryImpl(
                        userApi = get(),
                        userStore = get(),
                        dispatcher = get(),
                    ),
                    connectivityMonitor = ConnectivityMonitorImpl(
                        context = androidContext(),
                    )
                ),
                UserActionProcessor(
                    getUserContactRepository = GetUserContactRepositoryImpl(
                        userStore = get(),
                    )
                )
            ),
            reducers = listOf(
                DefaultReducer(
                    resources = androidContext().resources,
                )
            ),
            dispatcherProvider = get(),
        )
    }
}