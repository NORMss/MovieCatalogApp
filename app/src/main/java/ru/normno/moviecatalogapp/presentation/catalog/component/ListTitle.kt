package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.normno.moviecatalogapp.presentation.Dimens.heightTextName
import ru.normno.moviecatalogapp.ui.theme.AppTheme

fun ListTitle(
    title: String,
    scope: LazyGridScope,
) {
    scope.apply {
        item(
            span = {
                GridItemSpan(this.maxLineSpan)
            },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightTextName),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.titleNormal,
                    color = AppTheme.colorScheme.onBackground,
                )
            }
        }
    }
}