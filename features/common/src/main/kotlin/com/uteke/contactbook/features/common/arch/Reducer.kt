package com.uteke.contactbook.features.common.arch

interface Reducer<Mutation, ViewState> {
    operator fun invoke(mutation: Mutation, currentState: ViewState): ViewState
}
