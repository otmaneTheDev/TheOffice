package com.otmanethedev.theoffice.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.otmanethedev.domain.models.Keyboard

@Entity(
    tableName = "keyboard",
    foreignKeys = [ForeignKey(
        entity = DeskEntity::class,
        parentColumns = ["deskId"],
        childColumns = ["deskId"],
        onDelete = ForeignKey.SET_NULL
    )],
    indices = [Index(value = ["deskId"])]
)
class KeyboardEntity(
    @PrimaryKey(autoGenerate = true)
    var keyboardId: Int = 0,

    @ColumnInfo(name = "deskId")
    var deskId: Int? = null,

    @ColumnInfo(name = "model")
    var model: String
)

fun KeyboardEntity.toDomain(): Keyboard {
    return Keyboard(keyboardId,model)
}
