package com.example.attendancechecker.presentation.users_screen

import com.example.attendancechecker.data.local.dto.User

sealed class UserScreenEvent {

    object AddUser: UserScreenEvent()

    object DeleteUser: UserScreenEvent()

    data class setName(val name: String): UserScreenEvent()
    data class setSurname(val surname: String): UserScreenEvent()
    data class setEmail(val email: String): UserScreenEvent()
    object ToggleTrainer: UserScreenEvent()

    object ToggleEdit: UserScreenEvent()
    data class editUserInfo(val user: User): UserScreenEvent()
    object EditUser: UserScreenEvent()

    object ResetValues: UserScreenEvent()



}