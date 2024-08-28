package com.joze.fitnesstracking.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.joze.fitnesstracking.domain.manager.LocalUserManager
import com.joze.fitnesstracking.util.Constants
import com.joze.fitnesstracking.util.Constants.APP_ENTRY
import com.joze.fitnesstracking.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImple @Inject constructor(
    private val context: Context
) : LocalUserManager{
    override suspend fun saveAppEntry() {
        context.dataStore.edit {settings->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}


private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}