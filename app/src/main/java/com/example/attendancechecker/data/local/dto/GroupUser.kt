package com.example.attendancechecker.data.local.dto

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    primaryKeys = arrayOf("groupId", "userId"),
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Groups::class,
            parentColumns = arrayOf("groupId"),
            childColumns = arrayOf("groupId"),
            onDelete = ForeignKey.NO_ACTION // This ensures that deleting a User doesn't affect the Groups
        )
    ]
)
data class GroupUser(
    val groupId: Int,
    val userId: Int
)
