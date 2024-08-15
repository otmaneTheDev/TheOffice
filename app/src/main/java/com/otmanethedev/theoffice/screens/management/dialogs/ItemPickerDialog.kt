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
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.Screen
import com.otmanethedev.theoffice.components.DeskComponent
import com.otmanethedev.theoffice.components.KeyboardComponent
import com.otmanethedev.theoffice.components.ScreenComponent
import com.otmanethedev.theoffice.ui.theme.Typography

@Composable
fun <T> ItemPickerDialog(
    modifier: Modifier = Modifier,
    shouldShowDialog: MutableState<Boolean>,
    title: String,
    items: List<T>,
    onItemSelected: (T) -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        title = { Text(text = title, fontSize = Typography.bodyLarge.fontSize) },
        text = {
            if (items.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = items, key = { it.hashCode() }) { item ->
                        when (item) {
                            is Desk -> DeskComponent(
                                modifier = Modifier.clickable {
                                    onItemSelected(item)
                                    shouldShowDialog.value = false
                                },
                                desk = item
                            )

                            is Screen -> ScreenComponent(
                                modifier = Modifier.clickable {
                                    onItemSelected(item)
                                    shouldShowDialog.value = false
                                },
                                screen = item
                            )

                            is Keyboard -> KeyboardComponent(
                                modifier = Modifier.clickable {
                                    onItemSelected(item)
                                    shouldShowDialog.value = false
                                },
                                keyboard = item
                            )
                        }

                    }
                }
            } else {
                Text(text = "There are no free items")
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