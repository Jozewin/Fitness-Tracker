package com.joze.fitnesstracking.presentation.onBoarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joze.fitnesstracking.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val appEntryUseCase : AppEntryUseCases
): ViewModel(){

    fun onEvents(onBoardingScreenEvents: OnBoardingScreenEvents){
        when(onBoardingScreenEvents){
            OnBoardingScreenEvents.SaveAppEntry -> saveUserEntry()
        }
    }

    private fun saveUserEntry(){
        viewModelScope.launch {
            appEntryUseCase.saveAppEntry()
            Log.d("test", appEntryUseCase.readAppEntry.invoke().toString())
        }
    }
}