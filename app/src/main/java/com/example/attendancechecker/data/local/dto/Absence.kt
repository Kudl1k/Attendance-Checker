package com.example.attendancechecker.data.local.dto

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    primaryKeys = ["userId","trainingId"],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Training::class,
            parentColumns = arrayOf("trainingId"),
            childColumns = arrayOf("trainingId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Absence(
    val userId: Int,
    val trainingId: Int,
)
