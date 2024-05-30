package com.example.attendancechecker.domain.repository

import androidx.room.Delete
import com.example.attendancechecker.data.local.dto.GroupUser
import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.Groups
import com.example.attendancechecker.data.local.dto.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun upsertUser(user: User)

    suspend fun deleteUser(user: User)


    suspend fun deleteUserGroup(userGroup: GroupUser)

    suspend fun updateUser(user: User)

    fun getAllUsers(): Flow<List<User>>

    suspend fun getUser(id: Int): User?

    fun getAllTrainers(): Flow<List<User>>

    fun getUsers(): Flow<List<User>>

    suspend fun upsertGroup(group: Groups)

    suspend fun deleteGroup(groupName: String, groupId: Int, trainerId: Int, userIds: List<User>)

    suspend fun upsertGroupUserRef(userId: Int, groupId: Int)

    suspend fun insertGroupAndUsers(groupName: String,userIds: List<User>)

    fun getAllGroupsWithUsers(): Flow<List<GroupWithUserList>>

    suspend fun updateGroupAndUsers(groups: Groups, userIds: List<User>)

}