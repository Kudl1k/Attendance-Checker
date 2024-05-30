package com.example.attendancechecker.data.repository

import com.example.attendancechecker.data.local.ACDao
import com.example.attendancechecker.data.local.dto.GroupUser
import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.Groups
import com.example.attendancechecker.data.local.dto.User
import com.example.attendancechecker.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val acDao: ACDao
): MainRepository {

    override suspend fun upsertUser(user: User) {
        acDao.upsertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        acDao.deleteUser(user)
    }


    override suspend fun deleteUserGroup(userGroup: GroupUser) {
        acDao.deleteUserGroup(userGroup)
    }

    override suspend fun updateUser(user: User) {
        acDao.updateUser(user)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return acDao.getAllUsers()
    }

    override fun getUsers(): Flow<List<User>> {
        return acDao.getUsers()
    }

    override suspend fun getUser(id: Int): User? {
        return acDao.getUser(id)
    }

    override fun getAllGroupsWithUsers(): Flow<List<GroupWithUserList>> {
        return acDao.getAllGroupsWithUser()
    }

    override suspend fun deleteGroup(groupName: String, groupId: Int, trainerId: Int, userIds: List<User>){
        acDao.deleteGroup(groupName, groupId, trainerId, userIds)
    }

    override fun getAllTrainers(): Flow<List<User>> {
        return acDao.getAllTrainers()
    }

    override suspend fun upsertGroupUserRef(userId: Int, groupId: Int) {
        acDao.upsertGroupUserRef(GroupUser(groupId, userId))
    }

    override suspend fun upsertGroup(group: Groups) {
        acDao.upsertGroup(group)
    }


    override suspend fun insertGroupAndUsers(
        groupName: String,
        userIds: List<User>
    ) {
        acDao.insertGroupAndUsers(groupName, userIds)
    }

    override suspend fun updateGroupAndUsers(
        groups: Groups,
        userIds: List<User>
    ) {
        acDao.updateGroupAndUsers(groups, userIds)
    }
}