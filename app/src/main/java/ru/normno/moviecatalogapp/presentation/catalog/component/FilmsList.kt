@file:OptIn(ExperimentalLayoutApi::class)

package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.normno.moviecatalogapp.domain.model.Film
import ru.normno.moviecatalogapp.ui.theme.AppTheme

fun FilmsList(
    films: List<Film>,
    onClickFilm: (Film) -> Unit,
    columnsCount: Int = 1,
    scope: LazyListScope,
) {
    scope.apply {
        item {
            val itemSize: Dp =
                ((LocalConfiguration.current.screenWidthDp.dp / 2) - (AppTheme.size.normal) - (AppTheme.size.medium))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AppTheme.size.medium,
                    ),
                verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium),
                maxItemsInEachRow = columnsCount,
            ) {
                films.forEach { film ->
                    FilmCard(
                        imageUrl = film.imageUrl,
                        name = film.localizedName ?: "",
                        onClick = {
                            onClickFilm(film)
                        },
                        modifier = Modifier
                            .width(itemSize)
                    )
                }
            }
        }
    }
}