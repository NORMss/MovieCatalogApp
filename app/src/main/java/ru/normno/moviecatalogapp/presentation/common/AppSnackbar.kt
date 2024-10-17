package ru.normno.moviecatalogapp.presentation.common

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.normno.moviecatalogapp.presentation.Dimens.heightSnackbar
import ru.normno.moviecatalogapp.ui.theme.AppTheme
import ru.normno.moviecatalogapp.ui.theme.Roboto

@Composable
fun AppSnackbar(data: SnackbarData) {
    Snackbar(
        modifier = Modifier
            .padding(horizontal = AppTheme.size.normal)
            .padding(bottom = AppTheme.size.normal)
            .heightIn(min = heightSnackbar),
        contentColor = AppTheme.colorScheme.onPrimary,
        containerColor = AppTheme.colorScheme.tertiary,
        shape = AppTheme.shape.tertiaryContainer,
        action = {
            data.visuals.actionLabel?.let { actionLabel ->
                TextButton(
                    onClick = {
                        data.performAction()
                    },
                ) {
                    Text(
                        text = actionLabel,
                        fontSize = AppTheme.typography.body.fontSize,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colorScheme.onTertiary
                    )
                }
            }
        },
    ) {
        Text(
            text = data.visuals.message,
            fontSize = 15.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
        )
    }
}