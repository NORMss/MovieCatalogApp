package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items

fun GenresList2(
    genres: List<String>,
    selectGenre: String?,
    onClick: (String) -> Unit,
    scope: LazyGridScope,
) {
    scope.apply {
        items(
            items = genres,
            span = {
                GridItemSpan(this.maxLineSpan)
            }) { genre ->
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