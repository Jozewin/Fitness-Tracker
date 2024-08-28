package com.joze.fitnesstracking.domain.usecases.app_entry

import com.joze.fitnesstracking.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}