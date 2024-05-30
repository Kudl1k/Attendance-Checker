package com.example.attendancechecker.navgraph

sealed class Routes(val route: String) {

    object Trainings: Routes(
        route = "trainings_screen"
    )

    object TrainingsAddEdit: Routes(
        route = "trainings_add_screen"
    )

    object Groups: Routes(
        route = "groups_screen"
    )

    object GroupsAddEdit: Routes(
        route = "groups_add_screen"
    )

    object Users: Routes(
        route = "users_screen"
    )

    object UsersAddEdit: Routes(
        route = "users_add_screen"
    )

    object Main: Routes(
        route = "main_screen"
    )


}

val routes = listOf<Routes>(
    Routes.Trainings,
    Routes.Groups,
    Routes.Users
)