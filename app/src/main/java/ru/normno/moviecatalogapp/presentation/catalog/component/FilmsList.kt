package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import ru.normno.moviecatalogapp.domain.model.Film


fun FilmsList(
    films: List<Film>,
    onClickFilm: (Film) -> Unit,
    scope: LazyGridScope,
) {
    scope.apply {
        items(
            items = films,
            key = {
                it.id
            },
        ) { film ->
            FilmCard(
                imageUrl = film.imageUrl,
                name = film.localizedName ?: "",
                onClick = {
                    onClickFilm(film)
                },
            )
        }
    }
}