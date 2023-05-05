package com.example.phonebook.database

import com.example.phonebook.domain.model.ColorModel
import com.example.phonebook.domain.model.NEW_NOTE_ID
import com.example.phonebook.domain.model.NoteModel

class DbMapper {
    // Create list of NoteModels by pairing each note with a color
    fun mapNotes( // รับค่าของ noteDbModels colorDbModels และ return ค่าของ List<NoteModel>
        noteDbModels: List<NoteDbModel>, // รับ list ของ noteDbmodel
        colorDbModels: Map<Long, ColorDbModel> //รับ colorDbmodel
    ): List<NoteModel> = noteDbModels.map { // return กลับเป็น list ของ NoteModel map จะไปเรียก function ข้างล่าง
        val colorDbModel = colorDbModels[it.colorId]
            ?: throw RuntimeException("Color for colorId: ${it.colorId} was not found. Make sure that all colors are passed to this method")

        mapNote(it, colorDbModel)
    }

    // convert NoteDbModel to NoteModel
    fun mapNote(noteDbModel: NoteDbModel, colorDbModel: ColorDbModel): NoteModel { //
        val color = mapColor(colorDbModel)
        val isCheckedOff = with(noteDbModel) { if (canBeCheckedOff) isCheckedOff else null }
        return with(noteDbModel) { NoteModel(id, firstname, lastname, number, isCheckedOff, color) }
    }

    // convert list of ColorDdModels to list of ColorModels
    fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel> =
        colorDbModels.map { mapColor(it) }

    // convert ColorDbModel to ColorModel
    fun mapColor(colorDbModel: ColorDbModel): ColorModel = // รับ colorDbModel แล้วเปลี่ยนเป็น colorModel (มันมี with)
        with(colorDbModel) { ColorModel(id, name, hex) }

    // convert NoteModel back to NoteDbModel
    fun mapDbNote(note: NoteModel): NoteDbModel = //เปลี่ยนจาก notemodel ไปเป็น noteDbmodel เพื่อที่อัพเเดทไปยังฐานข้อมูล
        with(note) {
            val canBeCheckedOff = isCheckedOff != null
            val isCheckedOff = isCheckedOff ?: false
            if (id == NEW_NOTE_ID) // ถ้าจริงแสดงว่ามันเป็น Note ใหม่เอาก็สร้างมา
                NoteDbModel(
                    firstname = firstname,
                    lastname = lastname,
                    number = number,
                    canBeCheckedOff = canBeCheckedOff,
                    isCheckedOff = isCheckedOff,
                    colorId = color.id,
                    isInTrash = false
                )
            else //แสดงว่าเป็น id เดิม
                NoteDbModel(id, firstname, lastname, number, canBeCheckedOff, isCheckedOff, color.id, false )
        }
}