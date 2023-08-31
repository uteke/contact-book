package com.uteke.contactbook.features.userdetails.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.uteke.contactbook.features.common.view.ScreenScaffold
import com.uteke.contactbook.features.userdetails.R
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsAction
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewModel
import com.uteke.contactbook.features.userdetails.presentation.UserDetailsViewState

@Composable
internal fun UserDetailsScreen(
    viewModel: UserDetailsViewModel,
    id: String,
    onBack: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val viewStateFlow = viewModel.viewStateFlow
    val stateFlowLifecycleAware = remember(viewStateFlow, lifecycleOwner) {
        viewStateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewState by stateFlowLifecycleAware.collectAsState(initial = UserDetailsViewState.Loading)

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.process(action = UserDetailsAction.Load(id = id))
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
                .fillMaxSize()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                ),
            viewState = viewState,
            onReload = { viewModel.process(action = UserDetailsAction.Load(id = id)) },
        )
    }
}