package com.example.attendancechecker.data.local

import androidx.compose.ui.tooling.data.Group
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.attendancechecker.data.local.dto.Absence
import com.example.attendancechecker.data.local.dto.GroupUser
import com.example.attendancechecker.data.local.dto.Groups
import com.example.attendancechecker.data.local.dto.Training
import com.example.attendancechecker.data.local.dto.User


@Database(
    entities = [User::class,Groups::class,GroupUser::class,Training::class,Absence::class],
    version = 1
)
abstract class ACDatabase: RoomDatabase() {

    abstract val acDao: ACDao

}