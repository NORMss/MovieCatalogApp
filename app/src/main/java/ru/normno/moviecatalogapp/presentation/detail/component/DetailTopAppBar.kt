@file:OptIn(ExperimentalMaterial3Api::class)

package ru.normno.moviecatalogapp.presentation.detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import ru.normno.moviecatalogapp.presentation.Dimens.sizeTopAppBarShadow
import ru.normno.moviecatalogapp.ui.theme.AppTheme

@Composable
fun DetailTopAppBar(
    title: String?,
    onBackClick: () -> Unit,
) {
    Surface(shadowElevation = sizeTopAppBarShadow) {
        CenterAlignedTopAppBar(
            title = {
                title?.let {
                    Text(
                        text = title,
                        style = AppTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                    )
                }
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
    }
}