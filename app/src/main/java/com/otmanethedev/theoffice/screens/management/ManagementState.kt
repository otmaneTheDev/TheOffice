package com.otmanethedev.theoffice.screens.management

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.OfficeModel
import com.otmanethedev.domain.models.Person

@OptIn(ExperimentalFoundationApi::class)
data class ManagementState(
    val searchState: TextFieldState = TextFieldState(""),
    val freeDesks: List<Desk> = listOf(),
    val items: List<OfficeModel> = listOf()
)