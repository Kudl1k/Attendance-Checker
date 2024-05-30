package com.example.attendancechecker.presentation.groups_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendancechecker.data.local.dto.GroupUser
import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.Groups
import com.example.attendancechecker.data.local.dto.User
import com.example.attendancechecker.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GroupsScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private var _state = mutableStateOf(GroupsScreenState())
    val state: State<GroupsScreenState> = _state

    init {
        getAllTrainers()
        getAllGroups()
        getUsers()
    }


    fun onEvent(event: GroupsScreenEvent){
        when (event){
            GroupsScreenEvent.AddGroup -> {
                val name = _state.value.name
                val trainer = _state.value.trainer
                val group = Groups(
                    name = name,
                    trainerId = trainer!!.userId,
                )
                val selectedUsers = mutableListOf(trainer)
                _state.value.selectedUsers.forEach { selectedUsers.add(it) }
                viewModelScope.launch {
                    mainRepository.insertGroupAndUsers(name, selectedUsers)
                    getAllGroups()
                    getUsers()
                    getAllTrainers()
                    resetValues()
                }
            }
            is GroupsScreenEvent.setName -> {
                _state.value = _state.value.copy(
                    name = event.name
                )
            }
            is GroupsScreenEvent.setUser -> {
                _state.value = _state.value.copy(
                    trainer = event.user
                )
            }
            GroupsScreenEvent.RemoveTrainer -> {
                viewModelScope.launch {
                    mainRepository.deleteUserGroup(GroupUser(_state.value.id,_state.value.trainer!!.userId))
                }
                _state.value = _state.value.copy(
                    trainer = null
                )
            }
            GroupsScreenEvent.ToggleEdit -> {
                _state.value = _state.value.copy(
                    edit = !_state.value.edit
                )
            }
            is GroupsScreenEvent.editGroupInfo -> {
                val groupTrainer = getTrainer(event.group.userList)
                val selectedUsers = formatGroupList(event.group.userList)
                selectedUsers.forEach { _state.value.allUsers.remove(it) }
                _state.value = _state.value.copy(
                    name = event.group.groups.name,
                    trainer = groupTrainer,
                    id = event.group.groups.groupId,
                    selectedUsers = selectedUsers
                )
            }
            GroupsScreenEvent.EditGroup -> {
                val name = _state.value.name
                val trainer = _state.value.trainer
                val group = Groups(
                    name = name,
                    trainerId = trainer!!.userId,
                    groupId = _state.value.id
                )
                val selectedUsers = mutableListOf<User>(trainer)
                _state.value.selectedUsers.forEach { selectedUsers.add(it) }

                viewModelScope.launch {
                    mainRepository.updateGroupAndUsers(group, selectedUsers)
                    getAllGroups()
                    getUsers()
                    getAllTrainers()
                    resetValues()
                }
            }
            is GroupsScreenEvent.addUser -> {
                val selectedUser = event.user
                _state.value = _state.value.copy(
                    selectedUsers = (_state.value.selectedUsers + selectedUser).toMutableList(),
                    allUsers = (_state.value.allUsers - selectedUser).toMutableList()
                )
            }
            is GroupsScreenEvent.deleteUserConnection -> {
                val deleteUser = event.user
                _state.value = _state.value.copy(
                    selectedUsers = (_state.value.selectedUsers - deleteUser).toMutableList(),
                    allUsers = (_state.value.allUsers + deleteUser).toMutableList()
                )
                viewModelScope.launch {
                    mainRepository.deleteUserGroup(GroupUser(_state.value.id,deleteUser.userId))
                }
            }
            GroupsScreenEvent.DeleteGroup -> {
                viewModelScope.launch {
                    mainRepository.deleteGroup(
                        groupName = _state.value.name,
                        groupId = _state.value.id,
                        trainerId = _state.value.trainer!!.userId,
                        userIds = _state.value.selectedUsers
                    )
                }
            }
            GroupsScreenEvent.ResetValues -> {
                resetValues()
            }
        }
    }

    private fun getAllTrainers(){
        _state.value = _state.value.copy(allTrainers = mutableListOf())
        viewModelScope.launch {
            mainRepository
                .getAllTrainers()
                .collect { result ->
                    _state.value = _state.value.copy(
                        allTrainers = result.sortedBy { it.surname }.toMutableList()
                    )
                }
        }
    }

    private fun formatGroupList(list: List<User>): MutableList<User> {
        val mutableList = list.toMutableList()
        mutableList.removeIf { user -> user.trainer }
        return mutableList
    }


    private fun getAllGroups(){
        _state.value = _state.value.copy(groupsList = mutableListOf())
        viewModelScope.launch {
            mainRepository
                .getAllGroupsWithUsers()
                .collect { result ->
                    _state.value = _state.value.copy(
                        groupsList = result.sortedBy { it.groups.name }.toMutableList()
                    )
                }
        }
    }

    private fun getUsers(){
        _state.value = _state.value.copy(allUsers = mutableListOf())
        viewModelScope.launch {
            mainRepository
                .getUsers()
                .collect { result ->
                    _state.value = _state.value.copy(
                        allUsers = result.sortedBy { it.surname }.toMutableList()
                    )
                }
        }
    }

    private fun resetValues(){
        _state.value = _state.value.copy(
            name = "",
            trainer = null,
            id = 0,
            edit = false,
            selectedUsers = mutableListOf(),
        )
    }
}

fun getTrainer(list: List<User>): User? {
    val mutableList = list.toMutableList()
    mutableList.removeIf { user -> !user.trainer }
    return if (mutableList.isEmpty()) null else mutableList.first()
}