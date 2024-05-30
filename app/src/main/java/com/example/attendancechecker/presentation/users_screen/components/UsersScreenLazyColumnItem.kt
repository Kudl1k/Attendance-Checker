package com.example.attendancechecker.presentation.users_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.data.local.dto.User
import com.example.attendancechecker.navgraph.Routes
import com.example.attendancechecker.presentation.users_screen.UserScreenEvent

@Composable
fun UsersScreenLazyColumnItem(
    modifier: Modifier = Modifier,
    user: User,
    navController: NavController,
    onEvent: (UserScreenEvent) -> Unit
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
                    .size(32.dp),
                imageVector = Icons.Default.PermIdentity,
                contentDescription = null
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${user.surname} ${user.name}",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = if (user.trainer) stringResource(id = R.string.trainer) else stringResource(id = R.string.user),
                )
            }
            Spacer(modifier = modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp),
                onClick = {
                    onEvent(UserScreenEvent.ToggleEdit)
                    onEvent(UserScreenEvent.editUserInfo(user))
                    navController.navigate(Routes.UsersAddEdit.route)
                },
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
        }
    }
}
