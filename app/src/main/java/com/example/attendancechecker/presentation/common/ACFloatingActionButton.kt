package com.example.attendancechecker.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ACFloatingActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onEvent: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onEvent()
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "action_fab"
        )
    }
}