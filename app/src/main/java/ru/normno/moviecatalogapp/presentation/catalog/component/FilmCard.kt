package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import ru.normno.moviecatalogapp.presentation.Dimens.heightTextName
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun FilmCard(
    imageUrl: String?,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "film_image",
                modifier = Modifier
                    .clip(AppTheme.shape.primaryContainer)
                    .aspectRatio(3f / 4f)
                    .clickable {
                        onClick()
                    },
                contentScale = ContentScale.Crop,
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