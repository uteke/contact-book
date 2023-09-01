package com.uteke.contactbook.features.userlist.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.uteke.contactbook.features.common.view.ScreenScaffold
import com.uteke.contactbook.features.userlist.R
import com.uteke.contactbook.features.userlist.presentation.model.Action
import com.uteke.contactbook.features.userlist.presentation.UserListViewModel
import com.uteke.contactbook.features.userlist.presentation.model.Event
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun UserListScreen(
    viewModel: UserListViewModel,
    onItemClick: (String) -> Unit,
    onOpenEmail: (String) -> Unit,
    onOpenPhone: (String) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val viewStateFlow = viewModel.viewStateFlow
    val stateFlowLifecycleAware = remember(viewStateFlow, lifecycleOwner) {
        viewStateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewState by stateFlowLifecycleAware.collectAsState(initial = ViewState())
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
            viewModel.process(action = Action.CheckConnectivity)
        }
    }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.process(action = Action.Load)

            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is Event.OpenEmail -> onOpenEmail(event.email)
                    is Event.OpenPhone -> onOpenPhone(event.phone)
                    is Event.ToastError ->
                        Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    ScreenScaffold(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.userlist_screen_title),
        snackbarHostState = snackbarHostState,
    ) {
        UserListContent(
            modifier = Modifier.fillMaxSize(),
            viewState = viewState,
            onItemClick = onItemClick,
            onEmailClick = { viewModel.process(Action.EmailClick(uuid = it)) },
            onPhoneClick = { viewModel.process(Action.PhoneClick(uuid = it)) },
            onLoadNext = { viewModel.process(Action.Load) },
            onReload = { viewModel.process(Action.Reload) }
        )
    }
}

