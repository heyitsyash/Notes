package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//It is lifecycle aware and can bear the rotational changes and it constantly update the data LiveData
class NotesViewModel(application: Application) : AndroidViewModel(application) {

    //instance of the repo
    val repository : NoteRepository
    //we can get all notes from repository but to create instance of repo we need dao
    val allNotes : LiveData<List<Notes>>

    init {

        //getting dao from database
        val dao = NoteRoomDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(dao) //then passing in rep
        allNotes = repository.allNotes//finally getting all notes from repo
    }

    //as we know delete and insert fun in repo are suspend(coroutine) fun they can only be called by another suspend fun
    //or in an another background thread

    //so,here we are using coroutines Dispatcher.IO(means Input Output Operation)
    //so new thread will be created and after doing IO Operations it will be back to Ui thread
    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }

    fun insertNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}