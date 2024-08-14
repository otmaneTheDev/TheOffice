package com.otmanethedev.theoffice.data.local.utils

import androidx.room.Entity
import androidx.room.ForeignKey
import com.otmanethedev.theoffice.data.local.entities.DeskEntity
import com.otmanethedev.theoffice.data.local.entities.PersonEntity

@Entity(
    primaryKeys = ["personId", "deskId"],
    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["personId"],
            childColumns = ["personId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DeskEntity::class,
            parentColumns = ["deskId"],
            childColumns = ["deskId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class PersonDeskCrossRef(
    var personId: Int = 0,
    var deskId: Int = 0
)