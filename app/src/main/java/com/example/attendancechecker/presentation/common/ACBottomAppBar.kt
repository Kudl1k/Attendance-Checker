package com.example.attendancechecker.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.navgraph.Routes

@Composable
fun ACBottomAppBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == Routes.Users.route,
            onClick = {
                navController.navigate(Routes.Users.route){
                    this.popUpTo(Routes.Main.route)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "users"
                )
            },
            label = {
                Text(text = stringResource(id = R.string.users))
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == Routes.Groups.route,
            onClick = {
                navController.navigate(Routes.Groups.route){
                    this.popUpTo(Routes.Main.route)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Group,
                    contentDescription = "groups"
                )
            },
            label = {
                Text(text = stringResource(id = R.string.groups))
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == Routes.Trainings.route,
            onClick = {
                navController.navigate(Routes.Trainings.route){
                    this.popUpTo(Routes.Main.route)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "training"
                )
            },
            label = {
                Text(text = stringResource(id = R.string.trainings))
            }
        )


    }

}