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
import com.otmanethedev.theoffice.components.InputTextField
import com.otmanethedev.theoffice.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewItemDialog(
    modifier: Modifier = Modifier,
    shouldShowDialog: MutableState<Boolean>,
    titleText: String,
    hint: String,
    onConfirm: (String) -> Unit
) {
    val inputState = remember { TextFieldState("") }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        title = { Text(text = titleText, fontSize = Typography.bodyLarge.fontSize) },
        text = {
            InputTextField(state = inputState, hint = hint)
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(inputState.text.toString())
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