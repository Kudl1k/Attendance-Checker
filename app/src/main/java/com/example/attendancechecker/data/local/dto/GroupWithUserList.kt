package com.example.attendancechecker.data.local.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GroupWithUserList(
    @Embedded val groups: Groups,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "userId",
        associateBy = Junction(GroupUser::class)
    )
    val userList: List<User>
)
