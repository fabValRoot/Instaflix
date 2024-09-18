package com.example.instaflix.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instaflix.R
import com.example.instaflix.core.domain.models.Show
import com.example.instaflix.core.util.ShowPosterComposable


@Composable
fun ShowDetailScreen(
    show: Show,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ShowPosterComposable(show = show)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
            {
                Text(
                    text = stringResource(R.string.title, show.title, show.name),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.show_type, show.showType),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.category, show.category),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.language, show.language),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.release_date, show.releaseDate),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.overview, show.overview),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

        }

    }

}
