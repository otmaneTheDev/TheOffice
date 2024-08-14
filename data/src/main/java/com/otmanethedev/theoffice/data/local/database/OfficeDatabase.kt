package com.otmanethedev.theoffice.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otmanethedev.theoffice.data.local.daos.DeskDao
import com.otmanethedev.theoffice.data.local.daos.KeyboardDao
import com.otmanethedev.theoffice.data.local.daos.OfficeDao
import com.otmanethedev.theoffice.data.local.daos.PersonDao
import com.otmanethedev.theoffice.data.local.daos.ScreenDao
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.entities.KeyboardEntity
import com.otmanethedev.theoffice.data.local.entities.PersonEntity
import com.otmanethedev.theoffice.data.local.entities.ScreenEntity
import com.otmanethedev.theoffice.data.local.utils.PersonDeskCrossRef

@Database(
    entities = [
        PersonEntity::class,
        DeskEntity::class,
        KeyboardEntity::class,
        ScreenEntity::class,
        PersonDeskCrossRef::class
    ], version = 1
)
abstract class OfficeDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun deskDao(): DeskDao
    abstract fun keyboardDao(): KeyboardDao
    abstract fun screenDao(): ScreenDao
    abstract fun officeDao(): OfficeDao
}