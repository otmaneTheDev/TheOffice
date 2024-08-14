package com.otmanethedev.theoffice.screens.management

import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Person

sealed interface ManagementAction {
    data class AddPerson(val person: Person) : ManagementAction
    data object AddDesk : ManagementAction
    data object AddKeyboard : ManagementAction
    data object AddScreen : ManagementAction
    data class DeletePerson(val person: Person) : ManagementAction
    data class DeleteDesk(val desk: Desk) : ManagementAction
    data class AssignDeskToPerson(val desk: Desk, val person: Person) : ManagementAction
}