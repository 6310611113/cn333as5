package com.example.phonebook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ColorDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "hex") val hex: String,
    @ColumnInfo(name = "name") val name: String
) {
    companion object {
        val DEFAULT_COLORS = listOf(
            ColorDbModel(1, "#CE93D8", "Home"),
            ColorDbModel(2, "#E57373", "Friend"),
            ColorDbModel(3, "#F06292", "Work"),
            ColorDbModel(4, "#FFFFFF", "etc"),
        )
        val DEFAULT_COLOR = DEFAULT_COLORS[3] //ให้เป็นสี Default ขาว
    }
}