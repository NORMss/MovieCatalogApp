package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.normno.moviecatalogapp.ui.theme.AppTheme

fun GenresList(
    genres: List<String>,
    selectGenre: String?,
    onClick: (String) -> Unit,
    scope: LazyListScope,
) {
    scope.apply {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.size.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Жанры",
                    style = AppTheme.typography.titleNormal,
                    color = AppTheme.colorScheme.onBackground,
                )
            }
        }
        items(genres) { genre ->
            GenreCard(
                text = genre,
                isSelect = genre == selectGenre,
                onClick = {
                    onClick(genre)
                }
            )
        }
    }
}