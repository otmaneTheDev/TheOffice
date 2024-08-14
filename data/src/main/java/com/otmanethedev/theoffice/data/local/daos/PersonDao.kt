package com.otmanethedev.theoffice.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.otmanethedev.theoffice.data.local.entities.PersonEntity
import com.otmanethedev.theoffice.data.local.utils.PersonWithDesks
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert
    fun insertPerson(personEntity: PersonEntity)

    @Update
    fun updatePerson(personEntity: PersonEntity)

    @Query("DELETE from person where personId=:id")
    fun deletePerson(id: Int)

    @Query("SELECT * FROM person")
    fun getAllPersons(): Flow<List<PersonEntity>>

    @Transaction
    @Query("SELECT * FROM person WHERE personId = :personId")
    fun getPersonWithDesks(personId: Int): Flow<PersonWithDesks>

    @Query(
        """
        SELECT * FROM person 
        WHERE (:searchQuery IS NULL OR :searchQuery = '') 
        OR name LIKE '%' || :searchQuery || '%'
        """
    )
    fun searchPersons(searchQuery: String): Flow<List<PersonEntity>>

}