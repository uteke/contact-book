package com.uteke.contactbook.features.userdetails.di

import com.uteke.contactbook.features.userdetails.data.GetUserByIdRepositoryImpl
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsStateMapperImpl
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale

val userDetailsModule = module {
    viewModel {
        UserDetailsViewModel(
            getUserByIdRepository = GetUserByIdRepositoryImpl(
                userStore = get(),
            ),
            userDetailsStateMapper = UserDetailsStateMapperImpl(
                resources = androidContext().resources,
                locale = Locale.getDefault(),
            ),
            dispatcherProvider = get(),
        )
    }
}