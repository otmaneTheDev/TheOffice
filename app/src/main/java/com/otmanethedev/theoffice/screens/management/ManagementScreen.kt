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
import com.otmanethedev.theoffice.screens.management.dialogs.DeskPickerDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ManagementScreen(
    modifier: Modifier = Modifier,
    state: ManagementState,
    onAction: (ManagementAction) -> Unit
) {
    val shouldShowDialog = remember { mutableStateOf(false) }
    val selectedPerson = remember { mutableStateOf<Person?>(null) }

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
                            shouldShowDialog.value = true
                        },
                        onDeletePerson = {
                            onAction(ManagementAction.DeletePerson(it))
                        }
                    )

                    is Desk -> DeskComponent(desk = item,
                        onAssignToPerson = {

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

    if (shouldShowDialog.value) {
        DeskPickerDialog(
            shouldShowDialog = shouldShowDialog,
            desks = state.freeDesks,
            onDeskPicked = { desk ->
                selectedPerson.value?.let { person ->
                    onAction(ManagementAction.AssignDeskToPerson(desk, person))
                }
                shouldShowDialog.value = false
                selectedPerson.value = null
            }
        )
    }
}
