package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
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