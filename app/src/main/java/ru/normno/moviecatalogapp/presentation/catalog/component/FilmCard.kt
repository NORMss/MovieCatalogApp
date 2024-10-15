package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import ru.normno.moviecatalogapp.presentation.Dimens.heightTextName
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun FilmCard(
    imageUrl: String?,
    name: String,
    onClick: () -> Unit,
) {
    Column {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "film_image",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(3f / 4f)
                    .clip(AppTheme.shape.primaryContainer)
                    .clickable {
                        onClick()
                    }
            )
        }
        Spacer(
            modifier = Modifier
                .height(AppTheme.size.normal)
        )
        Text(
            text = name,
            style = AppTheme.typography.labelLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.heightIn(min = heightTextName)
        )
    }
}