package com.otmanethedev.theoffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.Person
import com.otmanethedev.domain.models.Screen
import com.otmanethedev.theoffice.components.ExpandableBtnMenu
import com.otmanethedev.theoffice.screens.management.ManagementAction
import com.otmanethedev.theoffice.screens.management.ManagementScreen
import com.otmanethedev.theoffice.screens.management.ManagementViewModel
import com.otmanethedev.theoffice.screens.management.dialogs.NewItemDialog
import com.otmanethedev.theoffice.ui.theme.TheOfficeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val shouldShowNewPersonDialog = remember { mutableStateOf(false) }
            val shouldShowNewDeskDialog = remember { mutableStateOf(false) }
            val shouldShowNewKeyboardDialog = remember { mutableStateOf(false) }
            val shouldShowNewScreenDialog = remember { mutableStateOf(false) }

            TheOfficeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        ExpandableBtnMenu(
                            addPerson = { shouldShowNewPersonDialog.value = true },
                            addDesk = { shouldShowNewDeskDialog.value = true },
                            addKeyboard = { shouldShowNewKeyboardDialog.value = true },
                            addScreen = { shouldShowNewScreenDialog.value = true }
                        )
                    }
                ) { innerPadding ->
                    ManagementScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = viewModel.state,
                        onAction = { viewModel.handleAction(it) }
                    )

                    if (shouldShowNewPersonDialog.value) {
                        NewItemDialog(
                            shouldShowDialog = shouldShowNewPersonDialog,
                            titleText = "Introduce name",
                            hint = "Name",
                            onConfirm = {
                                val person = Person(id = -1, name = it)
                                viewModel.handleAction(ManagementAction.AddPerson(person))
                            }
                        )
                    }
                    if (shouldShowNewDeskDialog.value) {
                        NewItemDialog(
                            shouldShowDialog = shouldShowNewDeskDialog,
                            titleText = "Introduce desk location",
                            hint = "Location",
                            onConfirm = {
                                val desk = Desk(id = -1, location = it)
                                viewModel.handleAction(ManagementAction.AddDesk(desk))
                            }
                        )
                    }
                    if (shouldShowNewKeyboardDialog.value) {
                        NewItemDialog(
                            shouldShowDialog = shouldShowNewKeyboardDialog,
                            titleText = "Introduce keyboard model",
                            hint = "Model",
                            onConfirm = {
                                val keyboard = Keyboard(id = -1, model = it)
                                viewModel.handleAction(ManagementAction.AddKeyboard(keyboard))
                            }
                        )
                    }
                    if (shouldShowNewScreenDialog.value) {
                        NewItemDialog(
                            shouldShowDialog = shouldShowNewScreenDialog,
                            titleText = "Introduce screen model",
                            hint = "Model",
                            onConfirm = {
                                val screen = Screen(id = -1, model = it)
                                viewModel.handleAction(ManagementAction.AddScreen(screen))
                            }
                        )
                    }
                }
            }
        }
    }
}