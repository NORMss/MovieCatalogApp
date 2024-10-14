package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.normno.moviecatalogapp.presentation.Dimens.heightGenreCard

@Composable
fun GenreCard(
    isSelect: Boolean,
    active: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightGenreCard),
    ) {

    }
}