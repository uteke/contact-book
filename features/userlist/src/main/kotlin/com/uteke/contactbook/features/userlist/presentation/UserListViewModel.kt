package com.uteke.contactbook.features.userlist.presentation

import androidx.lifecycle.ViewModel
import com.uteke.contactbook.features.common.arch.ActionProcessor
import com.uteke.contactbook.features.common.arch.Reducer
import com.uteke.contactbook.features.common.arch.model
import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.model.Event
import com.uteke.contactbook.features.userlist.presentation.model.Mutation
import com.uteke.contactbook.features.userlist.presentation.view.ViewState
import kotlinx.coroutines.flow.Flow

class UserListViewModel(
    actionProcessors: Collection<ActionProcessor<Action, Mutation, Event>>,
    reducers: Collection<Reducer<Mutation, ViewState>>,
    dispatcherProvider: DispatcherProvider,
    initialState: ViewState = ViewState(),
) : ViewModel() {
    private val model by model(actionProcessors, reducers, dispatcherProvider, initialState)

    internal val viewStateFlow: Flow<ViewState> get() = model.viewStateFlow
    internal val eventFlow: Flow<Event> get() = model.eventFlow

    fun process(action: Action) = model.process(action)
}