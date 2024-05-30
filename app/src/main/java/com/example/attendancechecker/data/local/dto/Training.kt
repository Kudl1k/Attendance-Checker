package com.example.attendancechecker.data.local.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Groups::class,
            parentColumns = arrayOf("groupId"),
            childColumns = arrayOf("groupId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Training(
    val date: String,
    val groupId: Int,
    @PrimaryKey(autoGenerate = true) val trainingId: Int
)
