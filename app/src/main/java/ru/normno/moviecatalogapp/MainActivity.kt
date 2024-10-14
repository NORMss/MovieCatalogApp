package ru.normno.moviecatalogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.normno.moviecatalogapp.presentation.navigator.NavGraph
import ru.normno.moviecatalogapp.presentation.navigator.Route
import ru.normno.moviecatalogapp.ui.theme.MovieCatalogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCatalogAppTheme {
                NavGraph(
                    startDestination = Route.Catalog,
                )
            }
        }
    }
}