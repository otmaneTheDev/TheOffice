package com.otmanethedev.theoffice.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.otmanethedev.theoffice.data.local.entities.ScreenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScreenDao {
    @Insert
    fun insertScreen(screenEntity: ScreenEntity)

    @Update
    fun updateScreen(screenEntity: ScreenEntity)

    @Delete
    fun deleteScreen(screenEntity: ScreenEntity)

    @Query("SELECT * FROM screen")
    fun getAllScreens(): Flow<List<ScreenEntity>>

    @Query("SELECT * FROM screen WHERE deskId = :deskId")
    fun getScreensForDesk(deskId: Int): Flow<List<ScreenEntity>>

    @Query(
        """
        SELECT * FROM screen 
        WHERE (:searchQuery IS NULL OR :searchQuery = '') 
        OR model LIKE '%' || :searchQuery || '%'
        """
    )
    fun searchScreens(searchQuery: String?): Flow<List<ScreenEntity>>
}