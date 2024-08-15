package com.otmanethedev.theoffice.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.otmanethedev.domain.models.Screen

@Entity(
    tableName = "screen",
    foreignKeys = [ForeignKey(
        entity = DeskEntity::class,
        parentColumns = ["deskId"],
        childColumns = ["deskId"],
        onDelete = ForeignKey.SET_NULL
    )],
    indices = [Index(value = ["deskId"])]
)
class ScreenEntity(
    @PrimaryKey(autoGenerate = true)
    var screenId: Int = 0,

    @ColumnInfo(name = "deskId")
    var deskId: Int? = null,

    @ColumnInfo(name = "model")
    var model: String
)

fun ScreenEntity.toDomain(): Screen {
    return Screen(screenId, model)
}