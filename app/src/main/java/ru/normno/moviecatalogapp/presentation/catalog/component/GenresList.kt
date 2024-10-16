package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items

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