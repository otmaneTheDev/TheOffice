package com.otmanethedev.theoffice.screens.management

import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.Person
import com.otmanethedev.domain.models.Screen

sealed interface ManagementAction {
    data class AddPerson(val person: Person) : ManagementAction
    data class AddDesk(val desk: Desk) : ManagementAction
    data class AddKeyboard(val keyboard: Keyboard) : ManagementAction
    data class AddScreen(val screen: Screen) : ManagementAction
    data class DeletePerson(val person: Person) : ManagementAction
    data class DeleteDesk(val desk: Desk) : ManagementAction
    data class AssignDeskToPerson(val desk: Desk, val person: Person) : ManagementAction
    data class AssignKeyboardToDesk(val keyboard: Keyboard, val desk: Desk) : ManagementAction
    data class AssignScreenToDesk(val screen: Screen, val desk: Desk) : ManagementAction
}