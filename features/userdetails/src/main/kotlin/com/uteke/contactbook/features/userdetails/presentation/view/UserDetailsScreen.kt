package com.uteke.contactbook.features.userdetails.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.uteke.contactbook.features.userdetails.R
import com.uteke.contactbook.features.userdetails.presentation.model.Action
import com.uteke.contactbook.features.userdetails.presentation.model.Event
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewModel

@Composable
internal fun UserDetailsScreen(
    viewModel: UserDetailsViewModel,
    id: String,
    onBack: () -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onLocationClick: (Double, Double) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val viewStateFlow = viewModel.viewStateFlow
    val stateFlowLifecycleAware = remember(viewStateFlow, lifecycleOwner) {
        viewStateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewState by stateFlowLifecycleAware.collectAsState(initial = ViewState.Loading)

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.process(action = Action.Load(id = id))

            viewModel.eventFlow.collect { event ->
                when (event) {
                    is Event.OpenEmail -> onEmailClick(event.email)
                    is Event.OpenLocation -> onLocationClick(event.latitude, event.longitude)
                    is Event.OpenPhone -> onPhoneClick(event.phone)
                    is Event.ToastError ->
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    ScreenScaffold(
        modifier = Modifier
            .fillMaxWidth(),
        title = stringResource(id = R.string.userdetails_screen_title),
        canGoBack = true,
        onBack = onBack,
    ) {
        UserDetailsContent(
            modifier = Modifier
                .fillMaxSize(),
            viewState = viewState,
            onReload = { viewModel.process(action = Action.Load(id = id)) },
            onEmailClick = { viewModel.process(action = Action.EmailClick(id = id)) },
            onPhoneClick = { viewModel.process(action = Action.PhoneClick(id = id)) },
            onLocationClick = { viewModel.process(action = Action.LocationClick(id = id)) },
        )
    }
}