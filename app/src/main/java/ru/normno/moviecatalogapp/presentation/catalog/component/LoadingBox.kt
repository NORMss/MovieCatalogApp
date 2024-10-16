package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.normno.moviecatalogapp.presentation.Dimens.sizeCircularProgressIndicator
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun LoadingBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(sizeCircularProgressIndicator),
            color = AppTheme.colorScheme.secondary,
            trackColor = Color.Unspecified,
        )
    }
}