package com.otmanethedev.theoffice.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.otmanethedev.theoffice.data.local.entities.KeyboardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeyboardDao {
    @Insert
    fun insertKeyboard(keyboardEntity: KeyboardEntity)

    @Update
    fun updateKeyboard(keyboardEntity: KeyboardEntity)

    @Delete
    fun deleteKeyboard(keyboardEntity: KeyboardEntity)

    @Query("SELECT * FROM keyboard")
    fun getAllKeyboards(): Flow<List<KeyboardEntity>>

    @Query("SELECT * FROM keyboard WHERE deskId = :deskId")
    fun getKeyboardsForDesk(deskId: Int): Flow<List<KeyboardEntity>>

    @Query(
        """
        SELECT * FROM keyboard 
        WHERE (:searchQuery IS NULL OR :searchQuery = '') 
        OR model LIKE '%' || :searchQuery || '%'
        """
    )
    fun searchKeyboards(searchQuery: String?): Flow<List<KeyboardEntity>>

    @Query("UPDATE keyboard SET deskId = :deskId WHERE keyboardId = :keyboardId")
    suspend fun assignKeyboardToDesk(keyboardId: Int, deskId: Int)

    @Query("SELECT * FROM keyboard WHERE deskId is NULL")
    fun getFreeKeyboards(): Flow<List<KeyboardEntity>>
}