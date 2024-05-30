package com.example.attendancechecker.presentation.groups_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.twotone.Group
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.attendancechecker.R
import com.example.attendancechecker.data.local.dto.GroupWithUserList
import com.example.attendancechecker.data.local.dto.Groups
import com.example.attendancechecker.data.local.dto.User
import com.example.attendancechecker.presentation.groups_screen.getTrainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreenLazyColumnComponent(
    modifier: Modifier = Modifier,
    group: GroupWithUserList,
    icon: ImageVector,
    onEvent: () -> Unit,
) {
    
    var trainerName = stringResource(id = R.string.no_trainer)

    if (group.userList.isNotEmpty()){
        val trainer = getTrainer(group.userList)
        if (trainer != null) {
            trainerName = "${trainer.surname} ${trainer.name}"
        }
    }
    
    ElevatedCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
                ,
                badge = {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = (group.userList.size-1).toString()
                        )
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Default.Group,
                    contentDescription = null
                )
            }

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = group.groups.name,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = trainerName,
                    overflow = TextOverflow.Ellipsis
                )
            }

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