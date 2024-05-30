package com.example.attendancechecker.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.attendancechecker.R
import com.example.attendancechecker.presentation.ui.theme.AttendanceCheckerTheme

@Composable
fun ACTextField(
    modifier: Modifier = Modifier,
    title: String,
    hint: String,
    value: String = "",
    inputType: InputType,
    onStateChange: (String) -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(value)
    }

    OutlinedTextField(
        modifier = modifier,
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onStateChange(textFieldValue)
        },
        singleLine = true,
        maxLines = 1,
        label = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis
            )
        },
        placeholder = {
            Text(
                text = hint,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        },
        trailingIcon = {



            when (inputType){
                InputType.NAME, InputType.SURNAME -> {
                    if (textFieldValue.length > 2){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "correct",
                        )
                    }
                }
                InputType.EMAIL -> {
                    if (textFieldValue.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "correct"
                        )
                    }
                }
            }
        },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
        ),
        keyboardOptions = if (inputType == InputType.EMAIL) KeyboardOptions(keyboardType = KeyboardType.Email) else KeyboardOptions()



    )
}


@Preview
@Composable
fun ACTextFieldPreview() {
    AttendanceCheckerTheme {
        ACTextField(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.name),
            hint = stringResource(id = R.string.enter_your_name),
            inputType = InputType.EMAIL,
            onStateChange = {

            }
        )
    }
}