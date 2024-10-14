package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GenresList(
    modifier: Modifier = Modifier,
    genres: List<String>,
    onClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Text(
                text = "Жанры",
            )
        }
        items(genres) { genre ->

        }
    }
}