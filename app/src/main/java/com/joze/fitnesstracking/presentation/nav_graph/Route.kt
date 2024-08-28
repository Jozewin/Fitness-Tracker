package com.joze.fitnesstracking.presentation.nav_graph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route : String,
    val arguments : List<NamedNavArgument> = emptyList()
){

    /* Two set of screen
    // 1. OnBoardingScreen which will be shown only once and
    // 2. is AppNavigation Screen where the main ui will be shown
    // The below two are the not routes but to differentiate that there are two ways
   */
    data object AppStartNavigation : Route(route = "appStartNavigation")

    data object TrackerNavigation : Route(route = "trackerNavigation")

    // The below are the routes for the screens
    data object OnBoardingScreen : Route(route = "onBoardingScreen")

    data object HomeScreen : Route(route = "homeScreen")



}