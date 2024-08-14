package com.otmanethedev.theoffice.screens.management.dialogs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.otmanethedev.domain.models.Person
import com.otmanethedev.theoffice.components.InputTextField
import com.otmanethedev.theoffice.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewPersonDialog(
    modifier: Modifier = Modifier,
    shouldShowDialog: MutableState<Boolean>,
    createNewPerson: (Person) -> Unit
) {
    val inputSate = remember { TextFieldState() }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        title = { Text(text = "Introduce user name", fontSize = Typography.bodyLarge.fontSize) },
        text = {
            InputTextField(state = inputSate, hint = "Name ")
        },
        confirmButton = {
            Button(
                onClick = {
                    createNewPerson(Person(id = -1, name = inputSate.text.toString()))
                    shouldShowDialog.value = false
                }
            ) {
                Text(
                    text = "Create",
                    color = Color.White
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    shouldShowDialog.value = false
                }
            ) {
                Text(
                    text = "Dismiss",
                    color = Color.Black
                )
            }
        }
    )
}