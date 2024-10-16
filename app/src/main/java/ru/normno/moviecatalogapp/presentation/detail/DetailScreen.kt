@file:OptIn(ExperimentalMaterial3Api::class)

package ru.normno.moviecatalogapp.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import ru.normno.moviecatalogapp.presentation.Dimens.heightImageFromDetail
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = name ?: "Unknown",
                        style = AppTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "navigate_up"
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = AppTheme.colorScheme.primary,
                    scrolledContainerColor = AppTheme.colorScheme.primary,
                    navigationIconContentColor = AppTheme.colorScheme.onPrimary,
                    titleContentColor = AppTheme.colorScheme.onPrimary,
                    actionIconContentColor = AppTheme.colorScheme.onPrimary,
                )
            )
        },
        containerColor = AppTheme.colorScheme.background,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = AppTheme.size.medium),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                item {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(AppTheme.shape.primaryContainer)
                            .height(heightImageFromDetail)
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                vertical = AppTheme.size.large,
                            ),
                    )
                }
                item {
                    Text(
                        text = localName ?: "Unknown",
                        style = AppTheme.typography.titleLarge,
                    )
                    Spacer(
                        Modifier
                            .height(AppTheme.size.normal)
                    )
                    Text(
                        text = "${
                            if (genres.isNotEmpty())
                                genres.joinToString(separator = ", ") else ""
                        }${year?.let { ", $year" } ?: ""}",
                        style = AppTheme.typography.labelNormal,
                        color = BlackCow,
                    )
                    Spacer(
                        Modifier
                            .height(10.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = rating?.let { String.format("%.1f", rating) } ?: "Unknown",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = Marine,
                        )
                        Spacer(
                            modifier = Modifier
                                .width(AppTheme.size.normal)
                        )
                        Text(
                            text = "КиноПоиск",
                            style = AppTheme.typography.titleExtraSmall,
                            color = Marine,
                        )
                    }
                    Spacer(
                        Modifier
                            .height(AppTheme.size.large),
                    )
                    Text(
                        text = description ?: "Unknown",
                    )
                }
            }
        }
    }
}