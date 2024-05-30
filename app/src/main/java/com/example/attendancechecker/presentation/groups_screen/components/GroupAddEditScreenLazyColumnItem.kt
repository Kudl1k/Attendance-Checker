package com.example.attendancechecker.presentation.groups_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendancechecker.R
import com.example.attendancechecker.data.local.dto.User
import com.example.attendancechecker.navgraph.Routes
import com.example.attendancechecker.presentation.groups_screen.GroupsScreenEvent
import com.example.attendancechecker.presentation.users_screen.UserScreenEvent

@Composable
fun GroupAddEditScreenLazyColumnItem(
    modifier: Modifier = Modifier,
    color: Color? = null,
    user: User,
    leadingIcon: ImageVector,
    icon: ImageVector,
    onEvent: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color ?: MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
                    .size(32.dp),
                imageVector = leadingIcon,
                contentDescription = null
            )
            Text(
                text = "${user.surname} ${user.name}",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp),
                onClick = {
                    onEvent()
                },
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    }
}