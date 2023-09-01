package com.uteke.contactbook.features.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified ViewState, reified Action, reified Mutation, reified Event> ViewModel.model(
    actionProcessors: Collection<ActionProcessor<Action, Mutation, Event>>,
    reducers: Collection<Reducer<Mutation, ViewState>>,
    dispatcherProvider: DispatcherProvider,
    initialState: ViewState,
) =
    ModelProperty(
        viewModel = this,
        actionProcessors = actionProcessors,
        reducers = reducers,
        dispatcherProvider = dispatcherProvider,
        viewMutableStateFlow = MutableStateFlow(initialState),
        eventChannel = Channel(Channel.BUFFERED),
    )

class ModelProperty<ViewState, Action, Mutation, Event>(
    private val viewModel: ViewModel,
    private val actionProcessors: Collection<ActionProcessor<Action, Mutation, Event>>,
    private val reducers: Collection<Reducer<Mutation, ViewState>>,
    private val dispatcherProvider: DispatcherProvider,
    private val viewMutableStateFlow: MutableStateFlow<ViewState>,
    private val eventChannel: Channel<Event>,
) : ReadOnlyProperty<Any, Model<ViewState, Action, Mutation, Event>> {
    override fun getValue(
        thisRef: Any,
        property: KProperty<*>,
    ): Model<ViewState, Action, Mutation, Event> =
        Model(
            actionProcessors = actionProcessors,
            reducers = reducers,
            coroutineScope = viewModel.viewModelScope,
            dispatcherProvider = dispatcherProvider,
            viewMutableStateFlow = viewMutableStateFlow,
            eventChannel = eventChannel,
        )
}
