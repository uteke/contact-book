package com.uteke.contactbook.features.userlist.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
internal fun UserCarView(
    modifier: Modifier = Modifier,
    state: UserState,
    onClick: (String) -> Unit,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp,
        ),
        modifier = modifier
            .clickable { onClick(state.uuid) }
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
            SubcomposeAsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp),
                model = state.pictureUrl,
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() },
                contentDescription = "",
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 16.dp,
                        end = 8.dp,
                    ),
            ) {
                Text(
                    text = state.fullname,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    text = state.gender,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                )

                Text(
                    text = state.age,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                )

                Text(
                    text = state.flag,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Column(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                    ),
            ) {
                SmallFloatingActionButton(
                    onClick = { onEmailClick(state.uuid) },
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    shape = CircleShape,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "null",
                        tint = Color.White,
                    )
                }

                SmallFloatingActionButton(
                    onClick = { onPhoneClick(state.uuid) },
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    shape = CircleShape,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "null",
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserCarViewPreview() =
    MaterialTheme {
        UserCarView(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            state = UserState(
                uuid = "uuid",
                pictureUrl = "https://randomuser.me/api/portraits/thumb/women/27.jpg",
                gender = "male",
                fullname = "Mr John Doe",
                age = "32 ans",
                flag = "\uD83C\uDDEB\uD83C\uDDF7",
            ),
            onClick = {},
            onEmailClick = {},
            onPhoneClick = {},
        )
    }
