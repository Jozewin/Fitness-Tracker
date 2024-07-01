package com.joze.fitnesstracking.presentation.onBoarding

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joze.fitnesstracking.data.repository.UserDetailsRepository
import com.joze.fitnesstracking.domain.model.UserDetails
import com.joze.fitnesstracking.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val appEntryUseCase : AppEntryUseCases,
    private val repository: UserDetailsRepository
): ViewModel(){

    var userDetails by mutableStateOf<UserDetails?>(null)
        private set

//    init {
//        viewModelScope.launch{
//            userDetails = repository.getUserDetails()
//        }
//    }
    fun saveUserProfile(name: String, age: Int, weight: Float) {
        viewModelScope.launch {
            val profile = UserDetails(name = name, age = age, weight = weight)
            repository.insertOrUpdate(profile)
            userDetails = profile
        }
    }
    fun onEvents(onBoardingScreenEvents: OnBoardingScreenEvents){
        when(onBoardingScreenEvents){
            OnBoardingScreenEvents.SaveAppEntry -> saveUserEntry()
        }
    }

    private fun saveUserEntry(){
        viewModelScope.launch {
            appEntryUseCase.saveAppEntry()
        }
    }
}