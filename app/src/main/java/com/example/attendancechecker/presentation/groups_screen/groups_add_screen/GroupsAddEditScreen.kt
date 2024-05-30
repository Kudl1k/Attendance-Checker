package com.example.attendancechecker.presentation.groups_screen.groups_add_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.presentation.common.ACFloatingActionButton
import com.example.attendancechecker.presentation.common.ACTextField
import com.example.attendancechecker.presentation.common.ACTopAppBar
import com.example.attendancechecker.presentation.common.InputType
import com.example.attendancechecker.presentation.groups_screen.GroupsScreenEvent
import com.example.attendancechecker.presentation.groups_screen.GroupsScreenState
import com.example.attendancechecker.presentation.groups_screen.components.GroupAddEditScreenLazyColumnItem

@Composable
fun GroupsAddEditScreen(
    navController: NavController,
    state: GroupsScreenState,
    onEvent: (GroupsScreenEvent) -> Unit
) {
    var groupName by remember {
        mutableStateOf(state.name)
    }





    Log.d("GroupScreenViewModel",state.toString())
    Scaffold(
        topBar = {
            ACTopAppBar(
                title = if (state.edit) stringResource(id = R.string.edit_group) else stringResource(id = R.string.add_group),
                onEvent = {
                    navController.popBackStack()
                    if (state.edit){
                        onEvent(GroupsScreenEvent.ToggleEdit)
                    }
                    Log.d("GroupViewModel","clicked back button")
                }
            )
        },
        floatingActionButton = {
            if (state.edit){
                ACFloatingActionButton(
                    icon = Icons.Default.Delete
                ) {
                    navController.popBackStack()
                    onEvent(GroupsScreenEvent.ToggleEdit)
                    onEvent(GroupsScreenEvent.DeleteGroup)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ACTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                title = stringResource(id = R.string.group_name),
                hint = "",
                value = groupName,
                inputType = InputType.NAME,
                onStateChange = { name ->
                    onEvent(GroupsScreenEvent.setName(name))
                    groupName = name
                }
            )
            if (state.trainer != null) {
                GroupAddEditScreenLazyColumnItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    user = state.trainer,
                    leadingIcon = Icons.Default.PermIdentity,
                    icon = Icons.Default.Cancel,
                    onEvent = {
                        onEvent(GroupsScreenEvent.RemoveTrainer)
                    }
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ){
                    items(state.selectedUsers){ user ->
                        GroupAddEditScreenLazyColumnItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            color = colorResource(id = R.color.selected_green),
                            user = user,
                            leadingIcon = Icons.Default.PermIdentity,
                            icon = Icons.Default.Cancel
                        ) {
                            onEvent(GroupsScreenEvent.deleteUserConnection(user))
                        }
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ){
                    items(state.allUsers){ user ->
                        GroupAddEditScreenLazyColumnItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            user = user,
                            leadingIcon = Icons.Default.PermIdentity,
                            icon = Icons.Default.Add
                        ) {
                            onEvent(GroupsScreenEvent.addUser(user))
                        }
                    }
                }

            } else {
                Text(
                    text = stringResource(id = R.string.select_trainer),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ){

                    if (state.allTrainers.isNotEmpty()){
                        items(state.allTrainers){ trainer ->
                            GroupAddEditScreenLazyColumnItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                                user = trainer,
                                leadingIcon = Icons.Default.PermIdentity,
                                icon = Icons.Default.Add,
                                onEvent = {
                                    onEvent(GroupsScreenEvent.setUser(trainer))
                                }
                            )
                        }
                    }
                }
            }
            Box(
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    modifier = Modifier
                        .padding(5.dp),
                    enabled = groupName.length > 2 && state.trainer != null,
                    onClick = {
                        if (state.edit){
                            onEvent(GroupsScreenEvent.EditGroup)
                            navController.popBackStack()
                        } else {
                            onEvent(GroupsScreenEvent.AddGroup)
                            navController.popBackStack()
                        }
                    },

                    ) {
                    Text(
                        text = if (state.edit) stringResource(id = R.string.edit_group) else stringResource(id = R.string.add_group)
                    )
                }
            }

        }
    }
}