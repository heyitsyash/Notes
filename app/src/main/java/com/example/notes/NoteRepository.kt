package com.example.notes

import androidx.lifecycle.LiveData

//Repository is single source of truth which takes data and give to view model which is lifecycle aware whether data is coming from
//an api or database or any other source view model doent have to take tension
class NoteRepository(private val notesDao: NotesDao) {

    //taking all notes from notesDao
    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    //interface dao fun are implemented here
    suspend fun insert(notes: Notes){
        notesDao.insertNote(notes)
    }

    //suspend fun in notedao can only be called by another suspend fun
    suspend fun delete(notes: Notes){
        notesDao.deleteNote(notes)
    }

}