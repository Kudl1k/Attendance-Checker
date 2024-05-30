package com.example.attendancechecker.presentation.users_screen

import com.example.attendancechecker.data.local.dto.User

data class UserScreenState(
    val listOfAllUsers: MutableList<User> = mutableListOf(),
    val edit: Boolean = false,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val trainer: Boolean = false,
    val id: Int = 0
)
