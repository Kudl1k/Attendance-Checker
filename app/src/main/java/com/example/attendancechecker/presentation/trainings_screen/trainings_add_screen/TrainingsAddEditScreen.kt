package com.example.attendancechecker.presentation.trainings_screen.trainings_add_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.presentation.common.ACTopAppBar
import com.example.attendancechecker.presentation.trainings_screen.TrainingsScreenEvent
import com.example.attendancechecker.presentation.trainings_screen.TrainingsScreenState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrainingsAddEditScreen(
    navController: NavController,
    state: TrainingsScreenState,
    onEvent: (TrainingsScreenEvent) -> Unit
) {
    var showDialog by remember {
        mutableStateOf(false) // Initialize to false so the dialog is not shown initially
    }
    val selectedDateTime = remember {
        mutableStateOf<LocalTime?>(null)
    }

    Scaffold(
        topBar = {
            ACTopAppBar(title = stringResource(id = R.string.add_group)) {
                navController.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = selectedDateTime.value?.toString() ?: "")
            Button(onClick = {
                showDialog = true // Set showDialog to true when the button is clicked
            }) {
                Text(text = "Open Dialog")
            }
            if (showDialog) { // Conditionally display the DateTimeDialog based on the value of showDialog
                DateTimeDialog(
                    state = rememberUseCaseState(visible = showDialog, onCloseRequest = { showDialog = false }, onDismissRequest = { showDialog = false }, onFinishedRequest = { showDialog = false }),
                    selection = DateTimeSelection.Time() { newDate ->
                        selectedDateTime.value = newDate
                    }
                )
            }
        }
    }
}
