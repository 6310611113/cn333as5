package com.example.phonebook.domain.model

const val NEW_NOTE_ID = -1L //-1 เพื่อระบุว่า เป็น note ใหม่
// file นี้ คือ note objet ที่จะเอาไปใช้ใน program จริงๆ
data class NoteModel(
    val id: Long = NEW_NOTE_ID, // This value is used for new notes
    val firstname: String = "",
    val lastname: String = "",
    val number: String = "",
    val isCheckedOff: Boolean? = null, // null represents that the note can't be checked off
    val color: ColorModel = ColorModel.DEFAULT

)