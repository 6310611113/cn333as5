package com.example.phonebook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, //ให้มันสร้างให้เอง
    @ColumnInfo(name = "firstname") val firstname: String,
    @ColumnInfo(name = "lastname") val  lastname: String,
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "can_be_checked_off") val canBeCheckedOff: Boolean,
    @ColumnInfo(name = "is_checked_off") val isCheckedOff: Boolean, //เช็คอยู่หรือเปล่า
    @ColumnInfo(name = "color_id") val colorId: Long,
    @ColumnInfo(name = "in_trash") val isInTrash: Boolean
) {
    companion object {
        val DEFAULT_NOTES = listOf(
            NoteDbModel(1, "RW Meeting", "Prepare sample project","555", false, false, 1, false),
        )
    }
}
