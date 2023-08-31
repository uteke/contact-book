package com.uteke.contactbook.features.userlist.presentation

import com.uteke.contactbook.features.common.monitor.ConnectivityMonitor
import com.uteke.contactbook.features.common.monitor.ConnectivityStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun fakeConnectivityMonitor(
    defaultStatus: ConnectivityStatus = ConnectivityStatus.AVAILABLE,
) = object : ConnectivityMonitor {
    override val statusFlow: Flow<ConnectivityStatus>
        get() = flowOf(defaultStatus)
}