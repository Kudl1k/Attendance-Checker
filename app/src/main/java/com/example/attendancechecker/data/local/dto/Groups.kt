package com.example.attendancechecker.data.local.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("trainerId"),
            onDelete = ForeignKey.SET_NULL
        )
    )
)
data class Groups(
    val name: String,
    val trainerId: Int? = null,
    @PrimaryKey(autoGenerate = true) val groupId: Int = 0
)
