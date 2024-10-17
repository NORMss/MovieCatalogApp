package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import ru.normno.moviecatalogapp.presentation.Dimens.heightGenreCard

fun LazyGridScope.GenresList(
    genres: List<String>,
    selectGenre: String?,
    parentHorizontalPadding: Dp,
    onClick: (String) -> Unit,
    scope: LazyGridScope,
) {
    scope.apply {
        items(
            items = genres,
            span = {
                GridItemSpan(this.maxLineSpan)
            }
        ) { genre ->
            GenreCard(
                text = genre,
                isSelect = genre == selectGenre,
                onClick = {
                    onClick(genre)
                },
                modifier = Modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            maxWidth = constraints.maxWidth + (parentHorizontalPadding * 2).roundToPx(),
                            maxHeight = heightGenreCard.roundToPx()
                        )
                    )
                    layout(placeable.width, placeable.height) {
                        placeable.place(0, 0)
                    }
                },
            )
        }
    }
}