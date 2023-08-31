package com.uteke.contactbook.features.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScreenScaffold(
    modifier: Modifier,
    title: String,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    canGoBack: Boolean = false,
    onBack: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

    Scaffold(
        modifier = modifier
            .background(Color.Black),
        backgroundColor = Color.White,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                    )
                },
                navigationIcon = {
                    if (canGoBack) {
                        IconButton(
                            modifier = Modifier,
                            onClick = onBack,
                        ) {
                            Icon(
                                modifier = Modifier.padding(start = 4.dp),
                                tint = Color.White,
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                },
                elevation = 2.dp,
            )
        },
        content = content,
    )
}

@Preview
@Composable
private fun ScreenScaffoldPreview() =
    MaterialTheme {
        ScreenScaffold(
            modifier = Modifier.fillMaxSize(),
            title = "App",
            content = {},
        )
    }