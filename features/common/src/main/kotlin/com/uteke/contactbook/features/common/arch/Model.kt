package com.uteke.contactbook.features.common.arch

import com.uteke.contactbook.features.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Model<ViewState, Action, Mutation, Event>(
    private val actionProcessors: Collection<ActionProcessor<Action, Mutation, Event>>,
    private val reducers: Collection<Reducer<Mutation, ViewState>>,
    private val coroutineScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    private val viewMutableStateFlow: MutableStateFlow<ViewState>,
    private val eventChannel: Channel<Event>,
) {
    val viewStateFlow: Flow<ViewState> = viewMutableStateFlow
    val eventFlow: Flow<Event> = eventChannel.receiveAsFlow()

    fun process(action: Action) {
        coroutineScope.launch(dispatcherProvider.default) {
            actionProcessors.map { actionProcessor -> actionProcessor(action) }
                .merge()
                .collect { (mutation, event) ->
                    mutation?.let(::handleMutation)
                    event?.let(eventChannel::trySend)
                }
        }
    }

    private fun handleMutation(mutation: Mutation) {
        reducers.asIterable()
            .forEach { reducer ->
                viewMutableStateFlow.update { currentState -> reducer(mutation, currentState) }
            }
    }
}
