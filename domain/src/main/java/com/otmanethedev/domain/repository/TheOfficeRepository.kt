package com.otmanethedev.domain.repository

import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.OfficeModel
import com.otmanethedev.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface TheOfficeRepository {

    suspend fun getOfficeObjects(query: String): Flow<List<OfficeModel>>

    suspend fun insertNewPerson()

    suspend fun insertNewDesk()

    suspend fun insertNewKeyboard()

    suspend fun insertNewScreen()

    fun deleteDesk(desk: Desk)

    fun deletePerson(person: Person)

    suspend fun getFreeDesks(): Flow<List<Desk>>

    fun assignDeskToPerson(desk: Desk, person: Person)
}