package com.otmanethedev.theoffice.screens.management

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.OfficeModel
import com.otmanethedev.domain.models.Screen

@OptIn(ExperimentalFoundationApi::class)
data class ManagementState(
    val searchState: TextFieldState = TextFieldState(""),
    val freeKeyboards: List<Keyboard> = listOf(),
    val freeScreens: List<Screen> = listOf(),
    val freeDesks: List<Desk> = listOf(),
    val items: List<OfficeModel> = listOf()
)