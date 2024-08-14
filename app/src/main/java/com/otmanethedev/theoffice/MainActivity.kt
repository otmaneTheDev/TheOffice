package com.otmanethedev.theoffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.otmanethedev.theoffice.components.ExpandableBtnMenu
import com.otmanethedev.theoffice.screens.management.ManagementAction
import com.otmanethedev.theoffice.screens.management.ManagementScreen
import com.otmanethedev.theoffice.screens.management.ManagementViewModel
import com.otmanethedev.theoffice.ui.theme.TheOfficeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TheOfficeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        ExpandableBtnMenu(
                            addPerson = { viewModel.handleAction(ManagementAction.AddPerson) },
                            addDesk = { viewModel.handleAction(ManagementAction.AddDesk) },
                            addKeyboard = { viewModel.handleAction(ManagementAction.AddKeyboard) },
                            addScreen = { viewModel.handleAction(ManagementAction.AddScreen) }
                        )
                    }
                ) { innerPadding ->
                    ManagementScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = viewModel.state,
                        onAction = { viewModel.handleAction(it) }
                    )
                }
            }
        }
    }
}