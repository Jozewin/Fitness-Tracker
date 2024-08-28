package com.joze.fitnesstracking.data.repository

import com.joze.fitnesstracking.data.local.UserDetailsDao
import com.joze.fitnesstracking.domain.model.UserDetails
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(
    private val userDetailsDao : UserDetailsDao
) {
    suspend fun insertOrUpdate(userDetails: UserDetails){
        userDetailsDao.insertOrUpdate(userDetails)
    }

    suspend fun getUserDetails() : UserDetails?{
        return userDetailsDao.getAllUsers()
    }
}