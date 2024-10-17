@file:OptIn(ExperimentalMaterial3Api::class)

package ru.normno.moviecatalogapp.presentation.catalog.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import ru.normno.moviecatalogapp.presentation.Dimens.sizeTopAppBarShadow
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun CatalogTopAppBar(title: String) {
    Surface(shadowElevation = sizeTopAppBarShadow) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = AppTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
            },
            colors = TopAppBarColors(
                containerColor = AppTheme.colorScheme.primary,
                scrolledContainerColor = AppTheme.colorScheme.primary,
                navigationIconContentColor = AppTheme.colorScheme.onPrimary,
                titleContentColor = AppTheme.colorScheme.onPrimary,
                actionIconContentColor = AppTheme.colorScheme.onPrimary,
            ),
        )
    }
}