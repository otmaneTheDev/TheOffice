package com.otmanethedev.theoffice.screens.management

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.Person
import com.otmanethedev.domain.models.Screen
import com.otmanethedev.domain.repository.TheOfficeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalFoundationApi::class)
class ManagementViewModel @Inject constructor(
    private val repository: TheOfficeRepository
) : ViewModel() {

    var state by mutableStateOf(ManagementState())
        private set

    init {
        fetchOfficeObjects()

        viewModelScope.launch {
            repository.getFreeDesks().collect {
                state = state.copy(freeDesks = it)
            }
        }

        viewModelScope.launch {
            repository.getFreeKeyboards().collect {
                state = state.copy(freeKeyboards = it)
            }
        }

        viewModelScope.launch {
            repository.getFreeScreen().collect {
                state = state.copy(freeScreens = it)
            }
        }
    }

    fun handleAction(action: ManagementAction) {
        when (action) {
            is ManagementAction.DeleteDesk -> deleteDesk(action.desk)
            is ManagementAction.DeletePerson -> deletePerson(action.person)
            is ManagementAction.AssignDeskToPerson -> assignDeskToPerson(action.desk, action.person)
            is ManagementAction.AddDesk -> addNewDesk(action.desk)
            is ManagementAction.AddKeyboard -> addNewKeyboard(action.keyboard)
            is ManagementAction.AddPerson -> addNewPerson(action.person)
            is ManagementAction.AddScreen -> addNewScreen(action.screen)
            is ManagementAction.AssignKeyboardToDesk -> assignKeyboardToDesk(action.keyboard, action.desk)
            is ManagementAction.AssignScreenToDesk -> assignScreenToDesk(action.screen, action.desk)
        }
    }

    private fun fetchOfficeObjects() {
        viewModelScope.launch {
            state.searchState.textAsFlow().collectLatest {
                repository.getOfficeObjects(it.toString()).collectLatest {
                    state = state.copy(items = it)
                }
            }
        }
    }

    private fun deleteDesk(desk: Desk) {
        viewModelScope.launch {
            repository.deleteDesk(desk)
        }
    }

    private fun deletePerson(person: Person) {
        viewModelScope.launch {
            repository.deletePerson(person)
        }
    }

    private fun assignDeskToPerson(desk: Desk, person: Person) {
        viewModelScope.launch {
            repository.assignDeskToPerson(desk, person)
            fetchOfficeObjects()
        }
    }

    private fun assignKeyboardToDesk(keyboard: Keyboard, desk: Desk) {
        viewModelScope.launch {
            repository.assignKeyboardToDesk(keyboard, desk)
            fetchOfficeObjects()
        }
    }

    private fun assignScreenToDesk(screen: Screen, desk: Desk) {
        viewModelScope.launch {
            repository.assignScreenToDesk(screen, desk)
            fetchOfficeObjects()
        }
    }

    private fun addNewDesk(desk: Desk) {
        viewModelScope.launch {
            repository.insertNewDesk(desk)
        }
    }

    private fun addNewKeyboard(keyboard: Keyboard) {
        viewModelScope.launch {
            repository.insertNewKeyboard(keyboard)
        }
    }

    private fun addNewPerson(person: Person) {
        viewModelScope.launch {
            repository.insertNewPerson(person)
        }
    }

    private fun addNewScreen(screen: Screen) {
        viewModelScope.launch {
            repository.insertNewScreen(screen)
        }
    }
}