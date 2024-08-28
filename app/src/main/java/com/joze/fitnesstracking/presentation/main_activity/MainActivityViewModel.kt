package com.joze.fitnesstracking.presentation.main_activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joze.fitnesstracking.domain.usecases.app_entry.AppEntryUseCases
import com.joze.fitnesstracking.presentation.nav_graph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel(){

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination : State<String> = _startDestination

    init {
        appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value =  Route.TrackerNavigation.route
            }else{
                _startDestination.value =  Route.AppStartNavigation.route
            }
            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

}