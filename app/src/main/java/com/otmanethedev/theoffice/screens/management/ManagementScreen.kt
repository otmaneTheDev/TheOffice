package com.otmanethedev.theoffice.screens.management

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.Person
import com.otmanethedev.domain.models.Screen
import com.otmanethedev.theoffice.components.DeskComponent
import com.otmanethedev.theoffice.components.InputTextField
import com.otmanethedev.theoffice.components.KeyboardComponent
import com.otmanethedev.theoffice.components.PersonComponent
import com.otmanethedev.theoffice.components.ScreenComponent
import com.otmanethedev.theoffice.screens.management.dialogs.ItemPickerDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ManagementScreen(
    modifier: Modifier = Modifier,
    state: ManagementState,
    onAction: (ManagementAction) -> Unit
) {
    val shouldShowDeskPickerDialog = remember { mutableStateOf(false) }
    val shouldShowKeyboardPickerDialog = remember { mutableStateOf(false) }
    val shouldShowScreenPickerDialog = remember { mutableStateOf(false) }

    val selectedPerson = remember { mutableStateOf<Person?>(null) }
    val selectedDesk = remember { mutableStateOf<Desk?>(null) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        InputTextField(
            modifier = Modifier.padding(16.dp),
            state = state.searchState,
            hint = "Search ..."
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = state.items, key = { it.hashCode() }) { item ->
                when (item) {
                    is Person -> PersonComponent(person = item,
                        onAssignDesk = {
                            selectedPerson.value = item
                            shouldShowDeskPickerDialog.value = true
                        },
                        onDeletePerson = {
                            onAction(ManagementAction.DeletePerson(it))
                        }
                    )

                    is Desk -> DeskComponent(desk = item,
                        onAssignToPerson = {
                            selectedDesk.value = item
                            shouldShowKeyboardPickerDialog.value = true
                        },
                        onAssignScreen = {
                            selectedDesk.value = item
                            shouldShowScreenPickerDialog.value = true
                        },
                        onDeletedDesk = {
                            onAction(ManagementAction.DeleteDesk(it))
                        }
                    )

                    is Screen -> ScreenComponent(screen = item)
                    is Keyboard -> KeyboardComponent(keyboard = item)
                    else -> Text(text = item.toString())
                }
            }
        }
    }

    if (shouldShowDeskPickerDialog.value) {
        ItemPickerDialog(
            shouldShowDialog = shouldShowDeskPickerDialog,
            items = state.freeDesks,
            title = "Choose a free desk",
            onItemSelected = { desk ->
                selectedPerson.value?.let { person ->
                    onAction(ManagementAction.AssignDeskToPerson(desk, person))
                }
                shouldShowDeskPickerDialog.value = false
                selectedPerson.value = null
            }
        )
    }

    if (shouldShowKeyboardPickerDialog.value) {
        ItemPickerDialog(
            shouldShowDialog = shouldShowKeyboardPickerDialog,
            items = state.freeKeyboards,
            title = "Choose a free keyboard",
            onItemSelected = { keyboard ->
                selectedDesk.value?.let { desk ->
                    onAction(ManagementAction.AssignKeyboardToDesk(keyboard, desk))
                }
                shouldShowKeyboardPickerDialog.value = false
                selectedDesk.value = null
            }
        )
    }

    if (shouldShowScreenPickerDialog.value) {
        ItemPickerDialog(
            shouldShowDialog = shouldShowScreenPickerDialog,
            items = state.freeScreens,
            title = "Choose a free screen",
            onItemSelected = { screen ->
                selectedDesk.value?.let { desk ->
                    onAction(ManagementAction.AssignScreenToDesk(screen, desk))
                }
                shouldShowScreenPickerDialog.value = false
                selectedDesk.value = null
            }
        )
    }
}
