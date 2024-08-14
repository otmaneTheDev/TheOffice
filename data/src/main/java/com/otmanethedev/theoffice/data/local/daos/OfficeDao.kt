package com.otmanethedev.theoffice.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.utils.PersonDeskCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface OfficeDao {

    @Insert
    fun insertPersonDeskCrossRef(personDeskCrossRef: PersonDeskCrossRef)

    @Delete
    fun deletePersonDeskCrossRef(personDeskCrossRef: PersonDeskCrossRef)

    @Query(
        """
        SELECT * FROM desk 
        WHERE deskId NOT IN (SELECT deskId FROM PersonDeskCrossRef)
    """
    )
    fun getUnassignedDesks(): Flow<List<DeskEntity>>

    @Query(
        """
        SELECT * FROM desk 
        INNER JOIN PersonDeskCrossRef ON desk.deskId = PersonDeskCrossRef.deskId
        WHERE PersonDeskCrossRef.personId = :personId
    """
    )
    fun getDesksForPerson(personId: Int): Flow<List<DeskEntity>>
}