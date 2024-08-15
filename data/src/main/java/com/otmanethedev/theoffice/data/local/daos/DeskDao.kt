package com.otmanethedev.theoffice.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.utils.DeskWithPersons
import kotlinx.coroutines.flow.Flow

@Dao
interface DeskDao {
    @Insert
    fun insertDesk(deskEntity: DeskEntity)

    @Update
    fun updateDesk(deskEntity: DeskEntity)

    @Query("DELETE from desk where deskId=:id")
    fun deleteDesk(id: Int)

    @Query("SELECT * FROM desk")
    fun getAllDesks(): Flow<List<DeskEntity>>

    @Transaction
    @Query("SELECT * FROM desk WHERE deskId = :deskId")
    fun getDeskWithPersons(deskId: Int): Flow<DeskWithPersons>

    @Query(
        """
        SELECT * FROM desk 
        WHERE (:searchQuery IS NULL OR :searchQuery = '') 
        OR location LIKE '%' || :searchQuery || '%'
        """
    )
    fun searchDesks(searchQuery: String?): Flow<List<DeskEntity>>
}