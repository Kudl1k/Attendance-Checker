package com.example.attendancechecker.presentation.groups_screen

import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.User

data class GroupsScreenState(
    val groupsList: MutableList<GroupWithUserList> = mutableListOf(),
    val allTrainers: MutableList<User> = mutableListOf(),
    val allUsers: MutableList<User> = mutableListOf(),
    val selectedUsers: MutableList<User> = mutableListOf(),
    val name: String = "",
    val trainer: User? = null,
    val id: Int = 0,
    val edit: Boolean = false

)
