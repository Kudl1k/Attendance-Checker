package com.example.attendancechecker.presentation.groups_screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.navgraph.Routes
import com.example.attendancechecker.presentation.common.ACBottomAppBar
import com.example.attendancechecker.presentation.common.ACFloatingActionButton
import com.example.attendancechecker.presentation.common.ACTextField
import com.example.attendancechecker.presentation.common.InputType
import com.example.attendancechecker.presentation.groups_screen.components.GroupAddEditScreenLazyColumnItem
import com.example.attendancechecker.presentation.groups_screen.components.GroupScreenLazyColumnComponent

@Composable
fun GroupsScreen(
    navController: NavController,
    state: GroupsScreenState,
    onEvent: (GroupsScreenEvent) -> Unit
) {
    Scaffold(
        bottomBar = {
            ACBottomAppBar(navController = navController)
        },
        floatingActionButton = {
            ACFloatingActionButton(
                icon = Icons.Default.Add
            ){
                if (state.edit){
                    onEvent(GroupsScreenEvent.ToggleEdit)
                }
                onEvent(GroupsScreenEvent.ResetValues)
                navController.navigate(Routes.GroupsAddEdit.route)
                Log.d("GroupViewModel","clicked add/edit button")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(vertical = 5.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(state.groupsList){ group ->
                    GroupScreenLazyColumnComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        group = group,
                        icon = Icons.Default.Edit,
                        onEvent = {
                            onEvent(GroupsScreenEvent.ToggleEdit)
                            onEvent(GroupsScreenEvent.editGroupInfo(group))
                            navController.navigate(Routes.GroupsAddEdit.route)
                        }
                    )
                }
            }
        }
    }
}