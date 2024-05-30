package com.example.attendancechecker.presentation.users_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendancechecker.navgraph.Routes
import com.example.attendancechecker.presentation.common.ACBottomAppBar
import com.example.attendancechecker.presentation.common.ACFloatingActionButton
import com.example.attendancechecker.presentation.users_screen.components.UsersScreenLazyColumnItem

@Composable
fun UsersScreen(
    navController: NavHostController,
    state: UserScreenState,
    onEvent: (UserScreenEvent) -> Unit
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
                    onEvent(UserScreenEvent.ToggleEdit)
                } else {
                    onEvent(UserScreenEvent.ResetValues)
                }
                navController.navigate(Routes.UsersAddEdit.route)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(vertical = 5.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ){
                items(state.listOfAllUsers){ user ->
                    UsersScreenLazyColumnItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        user = user,
                        navController = navController,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}