package com.example.attendancechecker.presentation.trainings_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.attendancechecker.navgraph.Routes
import com.example.attendancechecker.presentation.common.ACBottomAppBar
import com.example.attendancechecker.presentation.common.ACFloatingActionButton

@Composable
fun TrainingsScreen(
    navController: NavController,
    state: TrainingsScreenState,
    onEvent: (TrainingsScreenEvent) -> Unit
) {
    Scaffold(
        bottomBar = {
            ACBottomAppBar(navController = navController)
        },
        floatingActionButton = {
            ACFloatingActionButton(
                icon = Icons.Default.Add
            ){
                navController.navigate(Routes.TrainingsAddEdit.route)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add_button"
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun myPreview(){

}