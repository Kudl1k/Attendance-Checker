package com.example.attendancechecker.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.attendancechecker.presentation.groups_screen.GroupsScreen
import com.example.attendancechecker.presentation.groups_screen.GroupsScreenViewModel
import com.example.attendancechecker.presentation.groups_screen.groups_add_screen.GroupsAddEditScreen
import com.example.attendancechecker.presentation.trainings_screen.TrainingsScreen
import com.example.attendancechecker.presentation.trainings_screen.TrainingsScreenViewModel
import com.example.attendancechecker.presentation.trainings_screen.trainings_add_screen.TrainingsAddEditScreen
import com.example.attendancechecker.presentation.users_screen.UserScreenViewModel
import com.example.attendancechecker.presentation.users_screen.UsersScreen
import com.example.attendancechecker.presentation.users_screen.user_add_screen.UserAddEditScreen

@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()
    val userScreenViewModel: UserScreenViewModel = hiltViewModel()
    val userScreenState = userScreenViewModel.state.value

    val groupScreenViewModel: GroupsScreenViewModel = hiltViewModel()
    val groupScreenState = groupScreenViewModel.state.value

    val trainingsScreenViewModel: TrainingsScreenViewModel = hiltViewModel()
    val trainingsScreenState = trainingsScreenViewModel.state.value


    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        navigation(
            route = Routes.Main.route,
            startDestination = Routes.Trainings.route
        ){
            composable(
                route = Routes.Trainings.route
            ){
                TrainingsScreen(
                    navController = navController,
                    state = trainingsScreenState,
                    onEvent = trainingsScreenViewModel::onEvent
                )
            }
            composable(
                route = Routes.TrainingsAddEdit.route
            ){
                TrainingsAddEditScreen(
                    navController = navController,
                    state = trainingsScreenState,
                    onEvent = trainingsScreenViewModel::onEvent
                )
            }
            composable(
                route = Routes.Groups.route
            ){
                GroupsScreen(
                    navController = navController,
                    state = groupScreenState,
                    onEvent = groupScreenViewModel::onEvent
                )
            }
            composable(
                route = Routes.GroupsAddEdit.route
            ){
                GroupsAddEditScreen(
                    navController = navController,
                    state = groupScreenState,
                    onEvent = groupScreenViewModel::onEvent
                )
            }
            composable(
                route = Routes.Users.route
            ){
                UsersScreen(
                    navController = navController,
                    state = userScreenState,
                    onEvent = userScreenViewModel::onEvent
                )
            }

            composable(
                route = Routes.UsersAddEdit.route
            ){
                UserAddEditScreen(
                    navController = navController,
                    state = userScreenState,
                    onEvent = userScreenViewModel::onEvent
                )
            }

        }



    }


}