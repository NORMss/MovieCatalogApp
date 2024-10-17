package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.normno.moviecatalogapp.presentation.Dimens.heightGenreCard
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun GenreCard(
    text: String,
    isSelect: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(heightGenreCard)
            .background(if (isSelect) AppTheme.colorScheme.secondary else AppTheme.colorScheme.background)
            .clickable {
                onClick()
            }
            .padding(horizontal = AppTheme.size.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = text,
            style = AppTheme.typography.labelNormal,
        )
    }
}

@Preview
@Composable
private fun GenreCardPreview() {
    GenreCard(
        text = "TestText",
        isSelect = false,
        onClick = {

        }
    )
}

@Preview
@Composable
private fun GenreCardSelectPreview() {
    GenreCard(
        text = "TestText",
        isSelect = true,
        onClick = {

        }
    )
}