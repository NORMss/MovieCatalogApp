package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.ui.theme.AppTheme

fun FilmsList(
    modifier: Modifier = Modifier,
    films: List<Film>,
    onClickFilm: (Film) -> Unit,
    scope: LazyListScope,
) {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = AppTheme.size.medium),
//            contentPadding = PaddingValues(
//                horizontal = AppTheme.size.normal,
//                vertical = AppTheme.size.medium,
//            ),
//        ) {
//        }
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
                    text = "Фильмы",
                    style = AppTheme.typography.titleNormal,
                    color = AppTheme.colorScheme.onBackground,
                )
            }
            Spacer(
                modifier = Modifier
                    .height(AppTheme.size.normal),
            )
        }
        items(
            items = films,
            key = {
                it.id
            }
        ) { film ->
            FilmCard(
                imageUrl = film.imageUrl,
                name = film.name ?: "",
                onClick = {
                    onClickFilm(film)
                }
            )
        }
    }
}