package com.example.attendancechecker.presentation.groups_screen

import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.User

sealed class GroupsScreenEvent{

    data class setName(val name: String): GroupsScreenEvent()

    data class setUser(val user: User): GroupsScreenEvent()

    object AddGroup: GroupsScreenEvent()

    object RemoveTrainer: GroupsScreenEvent()


    object ToggleEdit: GroupsScreenEvent()
    object EditGroup: GroupsScreenEvent()

    data class editGroupInfo(val group: GroupWithUserList): GroupsScreenEvent()

    data class addUser(val user: User): GroupsScreenEvent()

    data class deleteUserConnection(val user: User): GroupsScreenEvent()

    object DeleteGroup: GroupsScreenEvent()

    object ResetValues: GroupsScreenEvent()

}
