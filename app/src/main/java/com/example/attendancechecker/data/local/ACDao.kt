package com.example.attendancechecker.data.local

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.example.attendancechecker.data.local.dto.GroupUser
import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.Groups
import com.example.attendancechecker.data.local.dto.User
import kotlinx.coroutines.flow.Flow


@Dao
interface ACDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Delete
    suspend fun deleteUserGroup(userGroup: GroupUser)

    @Delete
    suspend fun deleteGroup(groups: Groups)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE userId= :id")
    suspend fun getUser(id: Int): User?

    @Query("SELECT * FROM User WHERE trainer= 1")
    fun getAllTrainers(): Flow<List<User>>


    @Query("SELECT * FROM User WHERE trainer = 0")
    fun getUsers(): Flow<List<User>>


    @Upsert
    suspend fun upsertGroup(group: Groups): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGroup(group: Groups)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertGroupUserRef(userGroup: GroupUser)



    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGroupUserRef(userGroup: GroupUser)

    @Transaction
    suspend fun insertGroupAndUsers(groupName: String, userIds: List<User>) {
        val newGroup = Groups(name = groupName, trainerId = userIds[0].userId)
        val groupId = upsertGroup(newGroup)

        userIds.forEach { userId ->
            upsertGroupUserRef(GroupUser(groupId.toInt(), userId.userId))
        }
    }

    @Transaction
    suspend fun deleteGroup(groupName: String, groupId: Int, trainerId: Int, userIds: List<User>){
        userIds.forEach {
            deleteUserGroup(GroupUser(groupId,it.userId))
        }
        Log.d("GroupViewModel","usersDeleted")
        deleteUserGroup(GroupUser(groupId,trainerId))
        Log.d("GroupViewModel","${groupId} $trainerId")
        Log.d("GroupViewModel","trainerDeleted")
        deleteGroup(Groups(groupName,trainerId, groupId))
        Log.d("GroupViewModel","groupDeleted")
    }

    @Transaction
    suspend fun updateGroupAndUsers(groups: Groups, userIds: List<User>) {
        updateGroup(groups)
        // Now that we have the groupId, we can insert the GroupUser relationships
        userIds.forEach { userId ->
            upsertGroupUserRef(GroupUser(groups.groupId, userId.userId))
        }
    }


    @Query("SELECT * FROM groups")
    fun getAllGroupsWithUser(): Flow<List<GroupWithUserList>>


}