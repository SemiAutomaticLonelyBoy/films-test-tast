package com.example.filmstest.presentation.ui.features.filmInfo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.filmstest.domain.etities.FilmEntity
import com.example.filmstest.presentation.ui.components.CustomTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmInfoScreen(
    film: FilmEntity,
    navController: NavController,
) {
    val viewModel: FilmInfoViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.inputAction(FilmInfoViewModel.InputAction.OnStart(film))
        viewModel.action.collect { action ->
            when (action) {
                FilmInfoViewModel.OutputAction.GoBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                title = film.name,
                onBackClick = {
                    viewModel.inputAction(FilmInfoViewModel.InputAction.OnBackClick)
                },
            )
        },
    ) { paddingValues ->
        FilmInfoContent(
            state = viewModel.state.collectAsState().value,
            paddingValues = paddingValues,
        )
    }

}