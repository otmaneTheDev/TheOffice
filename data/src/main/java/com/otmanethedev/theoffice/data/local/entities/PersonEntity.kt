package com.otmanethedev.theoffice.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.otmanethedev.domain.models.Person

@Entity(tableName = "person")
class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    var personId: Int = 0,

    @ColumnInfo(name = "name")
    var name: String
)

fun PersonEntity.toDomain(): Person {
    return Person(personId,name)
}