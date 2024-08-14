package com.otmanethedev.theoffice.data.local.utils

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.entities.PersonEntity

class PersonWithDesks {
    @Embedded
    var personEntity: PersonEntity? = null

    @Relation(parentColumn = "personId",
        entityColumn = "deskId",
        associateBy = Junction(PersonDeskCrossRef::class))
    var deskEntities: List<DeskEntity>? = null
}