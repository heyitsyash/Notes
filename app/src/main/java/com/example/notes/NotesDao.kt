package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//dao is just data access object they only talk to table and can perform various queries
@Dao
interface NotesDao {

    @Insert
    suspend fun insertNote(notes: Notes)

    //suspend fun are called in background thread they dosen't lag the main thread the UI thread
    @Delete
    suspend fun deleteNote (notes: Notes)

    //this is a LiveData and activity is a Observer so any IO operation happens list get updated
    @Query("SELECT * FROM NOTES_TABLE ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}