package com.joze.fitnesstracking.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_details_table")
data class UserDetails(
    @PrimaryKey val id : Int = 0,
    val name : String,
    val age : Int,
    val weight : Float
)
