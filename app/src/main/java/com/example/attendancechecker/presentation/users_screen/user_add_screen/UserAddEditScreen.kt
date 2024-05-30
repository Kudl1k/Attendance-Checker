package com.example.attendancechecker.presentation.users_screen.user_add_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.presentation.common.ACFloatingActionButton
import com.example.attendancechecker.presentation.common.ACTextField
import com.example.attendancechecker.presentation.common.ACTopAppBar
import com.example.attendancechecker.presentation.common.InputType
import com.example.attendancechecker.presentation.users_screen.UserScreenEvent
import com.example.attendancechecker.presentation.users_screen.UserScreenState

@Composable
fun UserAddEditScreen(
    navController: NavController,
    state: UserScreenState,
    onEvent: (UserScreenEvent) -> Unit
) {
    var isChecked by remember {
        mutableStateOf(state.trainer)
    }
    val context = LocalContext.current
    val addMessage = stringResource(id = R.string.user_has_been_added)
    val editMessage = stringResource(id = R.string.user_has_been_edited)
    val deleteMessage = stringResource(id = R.string.user_has_been_deleted)

    var name by remember {
        mutableStateOf(state.name)
    }
    var surname by remember {
        mutableStateOf(state.surname)
    }
    var email by remember {
        mutableStateOf(state.email)
    }



    Scaffold(
        topBar = {
            ACTopAppBar(title = if (!state.edit) stringResource(id = R.string.user_add_screen) else stringResource(id = R.string.edit_user)) {
                if (state.edit){
                    onEvent(UserScreenEvent.ToggleEdit)
                }
                navController.popBackStack()
            }
        },
        floatingActionButton = {
            if (state.edit){
                ACFloatingActionButton(icon = Icons.Default.Delete) {
                    onEvent(UserScreenEvent.DeleteUser)
                    navController.popBackStack()
                    Toast.makeText(context, deleteMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 5.dp)
            ) {
                ACTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    title = stringResource(id = R.string.name),
                    hint = stringResource(id = R.string.enter_your_name),
                    value = name,
                    inputType = InputType.NAME,
                    onStateChange = {
                        name = it
                        onEvent(UserScreenEvent.setName(it))
                    }
                )
                ACTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp),
                    title = stringResource(id = R.string.surname),
                    hint = stringResource(id = R.string.enter_your_surname),
                    value = surname,
                    inputType = InputType.SURNAME,
                    onStateChange = {
                        surname = it
                        onEvent(UserScreenEvent.setSurname(it))
                    }
                )
            }
            ACTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                title = stringResource(id = R.string.email),
                hint = stringResource(id = R.string.enter_your_email),
                inputType = InputType.EMAIL,
                value = email,
                onStateChange = {
                    email = it
                    onEvent(UserScreenEvent.setEmail(it))
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.toggle_trainer),
                    modifier = Modifier.padding(end = 10.dp)
                )
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        onEvent(UserScreenEvent.ToggleTrainer)
                    }
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    onClick = {
                        if (!state.edit){
                            onEvent(UserScreenEvent.AddUser)
                            Toast.makeText(context,addMessage,Toast.LENGTH_LONG).show()
                        } else {
                            onEvent(UserScreenEvent.EditUser)
                            Toast.makeText(context,editMessage,Toast.LENGTH_LONG).show()
                        }

                        navController.popBackStack()

                    },
                    enabled = state.name.length > 2 && state.surname.length > 2 && state.email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
                ) {
                    Text(text = if (!state.edit) stringResource(id = R.string.add_user) else stringResource(id = R.string.edit_user))
                }
            }

        }
    }
    
    

}