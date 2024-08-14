package com.otmanethedev.theoffice.screens.management.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.theoffice.components.DeskComponent
import com.otmanethedev.theoffice.ui.theme.Typography

@Composable
fun DeskPickerDialog(
    modifier: Modifier = Modifier,
    shouldShowDialog: MutableState<Boolean>,
    desks: List<Desk>,
    onDeskPicked: (Desk) -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        title = { Text(text = "Choose a free desk", fontSize = Typography.bodyLarge.fontSize) },
        text = {
            if (desks.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = desks, key = { it.hashCode() }) { desk ->
                        DeskComponent(
                            modifier = Modifier.clickable {
                                onDeskPicked(desk)
                                shouldShowDialog.value = false
                            },
                            desk = desk
                        )
                    }
                }
            } else {
                Text(text = "There are no free desks")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    shouldShowDialog.value = false
                }
            ) {
                Text(
                    text = "Cancel",
                    color = Color.White
                )
            }
        }
    )
}