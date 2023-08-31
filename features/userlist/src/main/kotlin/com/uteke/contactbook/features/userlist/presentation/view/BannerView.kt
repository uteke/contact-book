package com.uteke.contactbook.features.userlist.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uteke.contactbook.features.userlist.R

@Composable
internal fun BannerView(
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Red,
        ),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(

            defaultElevation = 2.dp,
            pressedElevation = 4.dp,
        ),
        modifier = modifier
            .clickable(enabled = false, onClick = {}),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                ),
        ) {
            Icon(
                modifier = Modifier.padding(start = 4.dp),
                tint = Color.White,
                imageVector = Icons.Default.Info,
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 16.dp,
                        end = 8.dp,
                    ),
                text = stringResource(id = R.string.userlist_message_connection_unavailable),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun BannerViewPreview() =
    MaterialTheme {
        BannerView(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )
    }
