package com.example.attendancechecker.presentation.users_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancechecker.data.local.dto.User
import com.example.attendancechecker.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private var _state = mutableStateOf(UserScreenState())
    val state: State<UserScreenState> = _state

    init {
        getAllUsers()
    }


    fun onEvent(event: UserScreenEvent){
        when (event){
            UserScreenEvent.AddUser -> {
                val name = _state.value.name
                val surname = _state.value.surname
                val email = _state.value.email
                val trainer = _state.value.trainer

                val user = User(
                    name = name,
                    surname = surname,
                    email = email,
                    trainer = trainer
                )

                _state.value.listOfAllUsers.add(user)
                viewModelScope.launch {
                    mainRepository.upsertUser(user)
                    getAllUsers()
                    resetValues()
                }


            }
            UserScreenEvent.ToggleTrainer -> {
                _state.value = _state.value.copy(trainer = !_state.value.trainer)
            }
            is UserScreenEvent.setEmail -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is UserScreenEvent.setName -> {
                _state.value = _state.value.copy(name = event.name)
            }
            is UserScreenEvent.setSurname -> {
                _state.value = _state.value.copy(surname = event.surname)
            }
            is UserScreenEvent.editUserInfo -> {
                _state.value = _state.value.copy(
                    name = event.user.name,
                    surname = event.user.surname,
                    email = event.user.email,
                    trainer = event.user.trainer,
                    id = event.user.userId
                )
            }
            UserScreenEvent.EditUser -> {
                val name = _state.value.name
                val surname = _state.value.surname
                val email = _state.value.email
                val trainer = _state.value.trainer
                val id = _state.value.id
                val user = User(
                    name = name,
                    surname = surname,
                    email = email,
                    trainer = trainer,
                    userId = id
                )
                viewModelScope.launch {
                    mainRepository.updateUser(user = user)
                    getAllUsers()
                    resetValues()
                }


            }
            UserScreenEvent.ToggleEdit -> {
                _state.value = _state.value.copy(edit = !_state.value.edit)
                if (!_state.value.edit){
                    resetValues()
                }
            }
            UserScreenEvent.ResetValues -> {
                resetValues()
            }

            UserScreenEvent.DeleteUser -> {
                val name = _state.value.name
                val surname = _state.value.surname
                val email = _state.value.email
                val trainer = _state.value.trainer
                val id = _state.value.id
                val user = User(
                    name = name,
                    surname = surname,
                    email = email,
                    trainer = trainer,
                    userId = id
                )
                viewModelScope.launch {
                    mainRepository.deleteUser(user)
                }
            }
        }
    }

    private fun getAllUsers() {
        _state.value = _state.value.copy(listOfAllUsers = mutableListOf())
        viewModelScope.launch {
            mainRepository
                .getAllUsers()
                .collect { result ->

                    _state.value = _state.value.copy(
                        listOfAllUsers = result.sortedBy { it.surname }.toMutableList()
                    )
                }
        }
    }

    fun resetValues(){
        _state.value = _state.value.copy(
            name = "",
            surname = "",
            email = "",
            trainer = false,
            id = 0
        )
    }



}