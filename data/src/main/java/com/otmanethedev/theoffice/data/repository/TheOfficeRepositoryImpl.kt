package com.otmanethedev.theoffice.data.repository

import android.util.Log
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.domain.models.OfficeModel
import com.otmanethedev.domain.models.Person
import com.otmanethedev.domain.repository.TheOfficeRepository
import com.otmanethedev.theoffice.data.local.daos.DeskDao
import com.otmanethedev.theoffice.data.local.daos.KeyboardDao
import com.otmanethedev.theoffice.data.local.daos.OfficeDao
import com.otmanethedev.theoffice.data.local.daos.PersonDao
import com.otmanethedev.theoffice.data.local.daos.ScreenDao
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.entities.KeyboardEntity
import com.otmanethedev.theoffice.data.local.entities.PersonEntity
import com.otmanethedev.theoffice.data.local.entities.ScreenEntity
import com.otmanethedev.theoffice.data.local.entities.toDomain
import com.otmanethedev.theoffice.data.local.utils.PersonDeskCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TheOfficeRepositoryImpl @Inject constructor(
    private val officeDao: OfficeDao,
    private val personDao: PersonDao,
    private val deskDao: DeskDao,
    private val keyboardDao: KeyboardDao,
    private val screenDao: ScreenDao
) : TheOfficeRepository {

    override suspend fun getOfficeObjects(query: String): Flow<List<OfficeModel>> {
        return combine(
            personDao.searchPersons(query),
            deskDao.searchDesks(query),
            keyboardDao.searchKeyboards(query),
            screenDao.searchScreens(query)
        ) { persons, desks, keyboards, screens ->
            val officeObjects = mutableListOf<OfficeModel>()

            officeObjects.addAll(persons.map {
                val personDesks = officeDao.getDesksForPerson(it.personId).first()
                it.toDomain().apply {
                    assignedDesks = personDesks.map { it.toDomain() }
                }
            })

            officeObjects.addAll(desks.map { it.toDomain() })
            officeObjects.addAll(keyboards.map { it.toDomain() })
            officeObjects.addAll(screens.map { it.toDomain() })

            officeObjects.toList()
        }
    }

    override suspend fun insertNewPerson() {
        withContext(Dispatchers.IO) {
            personDao.insertPerson(PersonEntity(name = "Dwight"))
        }
    }

    override suspend fun insertNewDesk() {
        withContext(Dispatchers.IO) {
            deskDao.insertDesk(DeskEntity(location = "Room-F1-W2"))
        }
    }

    override suspend fun insertNewKeyboard() {
        withContext(Dispatchers.IO) {
            keyboardDao.insertKeyboard(KeyboardEntity(model = "logitech-pro"))
        }
    }

    override suspend fun insertNewScreen() {
        withContext(Dispatchers.IO) {
            screenDao.insertScreen(ScreenEntity(model = "AOC IPS 24\""))
        }
    }

    override fun deleteDesk(desk: Desk) {
        CoroutineScope(Dispatchers.IO).launch {
            deskDao.deleteDesk(desk.id)
        }
    }

    override fun deletePerson(person: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            personDao.deletePerson(person.id)
        }
    }

    override suspend fun getFreeDesks(): Flow<List<Desk>> {
        return withContext(Dispatchers.IO) {
            officeDao.getUnassignedDesks().map {
                it.map { it.toDomain() }
            }
        }
    }

    override fun assignDeskToPerson(desk: Desk, person: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            officeDao.insertPersonDeskCrossRef(PersonDeskCrossRef(person.id, desk.id))
        }
    }
}