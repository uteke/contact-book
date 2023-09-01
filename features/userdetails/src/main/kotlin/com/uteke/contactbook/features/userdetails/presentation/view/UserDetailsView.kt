package com.uteke.contactbook.features.userdetails.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.uteke.contactbook.features.userdetails.R

@Composable
internal fun UserDetailsView(
    modifier: Modifier = Modifier,
    viewState: ViewState.Content,
    onEmailClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onLocationClick: () -> Unit,
) {
    Column {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxHeight(.3f)
                .fillMaxWidth(),
            model = viewState.pictureUrl,
            contentScale = ContentScale.Crop,
            loading = { CircularProgressIndicator() },
            contentDescription = "",
        )

        Column(
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
        ) {
            Text(
                text = viewState.fullname,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Text(
                text = viewState.username,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                    ),
                horizontalArrangement = Arrangement.Center,
            ) {
                SmallFloatingActionButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onEmailClick,
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
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onPhoneClick,
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

                SmallFloatingActionButton(
                    onClick = onLocationClick,
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    shape = CircleShape,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "null",
                        tint = Color.White,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_gender),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.gender,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_nationality),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.nationality,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_date),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.dateOfBirth,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_age),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.age,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_address),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.address,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_city),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.city,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_state),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.state,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_postcode),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.postcode,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.userdetails_label_country),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                        ),
                    text = viewState.country,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )
            }
        }
    }
}

@Preview
@Composable
private fun UserDetailsViewPreview() =
    MaterialTheme {
        UserDetailsView(
            modifier = Modifier,
            viewState = ViewState.Content(
                pictureUrl = "https://randomuser.me/api/portraits/large/women/15.jpg",
                username = "yellowbutterfly577",
                gender = "female",
                fullname = "Mrs Frances Herrera",
                nationality = "France",
                dateOfBirth = "1983/05/27",
                age = "40 y/o",
                address = "5740 W Dallas St",
                city = "Wollongong",
                state = "Australian Capital Territory",
                country = "Australia",
                postcode = "3018",
            ),
            onEmailClick = {},
            onPhoneClick = {},
            onLocationClick = {},
        )
    }