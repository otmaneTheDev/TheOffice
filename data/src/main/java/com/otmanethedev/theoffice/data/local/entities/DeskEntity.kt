package com.otmanethedev.theoffice.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.otmanethedev.domain.models.Desk

@Entity(tableName = "desk")
class DeskEntity(
    @PrimaryKey(autoGenerate = true)
    var deskId: Int = 0,

    @ColumnInfo(name = "location")
    var location: String
)

fun DeskEntity.toDomain(): Desk {
    return Desk(deskId, location)
}