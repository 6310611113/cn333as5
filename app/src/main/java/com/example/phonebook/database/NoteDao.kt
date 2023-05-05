package com.example.phonebook.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteDbModel") // ดึง note ทั้งหมด
    fun getAllSync(): List<NoteDbModel> // รอให้มันเสร็จก่อน แบบ Sync

    @Query("SELECT * FROM NoteDbModel WHERE id IN (:noteIds)") // ระ id เอาเฉพาะบางส่วน
    fun getNotesByIdsSync(noteIds: List<Long>): List<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id LIKE :id")
    fun findByIdSync(id: Long): NoteDbModel // เอาid เดียว note เดียว

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteDbModel: NoteDbModel) //insert แล้วมันซ้ำก็ให้ replace ของเดิมไป

    @Insert
    fun insertAll(vararg noteDbModel: NoteDbModel)

    @Query("DELETE FROM NoteDbModel WHERE id LIKE :id")
    fun delete(id: Long) // ลบ note ตาม id ที่ระบุ

    @Query("DELETE FROM NoteDbModel WHERE id IN (:noteIds)")
    fun delete(noteIds: List<Long>) //ลบหลายตัวพร้อมๆกัน
}