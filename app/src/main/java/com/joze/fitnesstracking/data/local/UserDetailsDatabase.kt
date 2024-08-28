package com.joze.fitnesstracking.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joze.fitnesstracking.domain.model.UserDetails

@Database(entities = [UserDetails::class], version = 1)
abstract class UserDetailsDatabase : RoomDatabase(){

    abstract val userDetailsDao : UserDetailsDao

}