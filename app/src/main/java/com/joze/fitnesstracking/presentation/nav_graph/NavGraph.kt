package com.joze.fitnesstracking.presentation.nav_graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.joze.fitnesstracking.presentation.home_screen.HomeScreen
import com.joze.fitnesstracking.presentation.onBoarding.OnBoardingScreenViewModel
import com.joze.fitnesstracking.presentation.onBoarding.onBoardingScreen

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController ,
        startDestination = startDestination
    ){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(route = Route.OnBoardingScreen.route){
                val viewModel : OnBoardingScreenViewModel = hiltViewModel()
                onBoardingScreen(viewModel)
            }
        }

        navigation(
            route = Route.TrackerNavigation.route,
            startDestination = Route.HomeScreen.route
        ){
            composable(route = Route.HomeScreen.route){
                HomeScreen()
            }
        }
    }
}