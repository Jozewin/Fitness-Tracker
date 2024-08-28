package com.joze.fitnesstracking.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.joze.fitnesstracking.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(user : UserDetails)

    @Query("Select * from user_details_table WHERE id=0")
    fun getAllUsers() :  UserDetails?
}