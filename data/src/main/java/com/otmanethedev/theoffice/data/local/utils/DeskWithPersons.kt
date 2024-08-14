package com.otmanethedev.theoffice.data.local.utils

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.entities.PersonEntity

class DeskWithPersons {
    @Embedded
    var deskEntity: DeskEntity? = null

    @Relation(parentColumn = "deskId",
        entityColumn = "personId",
        associateBy = Junction(PersonDeskCrossRef::class))
    var personEntities: List<PersonEntity>? = null
}