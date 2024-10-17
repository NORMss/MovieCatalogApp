package ru.normno.moviecatalogapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.normno.moviecatalogapp.R
import ru.normno.moviecatalogapp.presentation.detail.component.DetailTopAppBar
import ru.normno.moviecatalogapp.ui.theme.AppTheme
import ru.normno.moviecatalogapp.ui.theme.BlackCow
import ru.normno.moviecatalogapp.ui.theme.Marine
import ru.normno.moviecatalogapp.ui.theme.Roboto

@Composable
fun DetailScreen(
    name: String?,
    imageUrl: String?,
    localName: String?,
    genres: List<String>,
    year: Int?,
    rating: Double?,
    description: String?,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = name,
                onBackClick = onBackClick,
            )
        },
        containerColor = AppTheme.colorScheme.background,
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = AppTheme.size.medium),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        error = painterResource(R.drawable.image_load_error),
                        modifier = Modifier
                            .padding(
                                vertical = AppTheme.size.large,
                            )
                            .clip(AppTheme.shape.secondaryContainer)
                            .fillMaxWidth(0.5f)
                            .aspectRatio(9f / 16f),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            item {
                Text(
                    text = localName ?: "",
                    style = AppTheme.typography.titleLarge,
                )
                Spacer(
                    Modifier
                        .height(AppTheme.size.normal)
                )
                Text(
                    text = "${
                        if (genres.isNotEmpty())
                            genres.joinToString(separator = ", ") + ", " else ""
                    }${year ?: ""}",
                    style = AppTheme.typography.labelNormal,
                    color = BlackCow,
                )
                Spacer(
                    Modifier
                        .height(10.dp)
                )
                rating?.let { rating ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = String.format("%.1f", rating),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = Marine,
                            modifier = Modifier.alignByBaseline()
                        )
                        Spacer(
                            modifier = Modifier
                                .width(AppTheme.size.normal)
                        )
                        Text(
                            text = stringResource(R.string.kinopoisk),
                            style = AppTheme.typography.titleExtraSmall,
                            color = Marine,
                            modifier = Modifier.alignByBaseline()
                        )
                    }
                }
                Spacer(
                    Modifier
                        .height(AppTheme.size.large),
                )
                Text(
                    text = description ?: "",
                    style = AppTheme.typography.body,
                    color = AppTheme.colorScheme.onBackground,
                )
            }
        }
    }
}