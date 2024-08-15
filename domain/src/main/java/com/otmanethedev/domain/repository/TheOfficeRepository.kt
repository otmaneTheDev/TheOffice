package com.otmanethedev.domain.repository

import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.domain.models.OfficeModel
import com.otmanethedev.domain.models.Person
import com.otmanethedev.domain.models.Screen
import kotlinx.coroutines.flow.Flow

interface TheOfficeRepository {

    suspend fun getOfficeObjects(query: String): Flow<List<OfficeModel>>

    suspend fun insertNewPerson(person: Person)

    suspend fun insertNewDesk(desk: Desk)

    suspend fun insertNewKeyboard(keyboard: Keyboard)

    suspend fun insertNewScreen(screen: Screen)

    fun deleteDesk(desk: Desk)

    fun deletePerson(person: Person)

    suspend fun getFreeDesks(): Flow<List<Desk>>

    suspend fun getFreeKeyboards():Flow<List<Keyboard>>

    suspend fun getFreeScreen():Flow<List<Screen>>

    fun assignDeskToPerson(desk: Desk, person: Person)

    fun assignKeyboardToDesk(keyboard: Keyboard, desk: Desk)

    fun assignScreenToDesk(screen: Screen, desk: Desk)
}