package com.example.attendancechecker.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    val name: String,
    val surname: String,
    val email: String,
    val trainer: Boolean,
    @PrimaryKey(autoGenerate = true) val userId: Int = 0
)
