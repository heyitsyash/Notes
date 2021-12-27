package com.example.notes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//Implementing Adapter Delete fun
class MainActivity : AppCompatActivity(), NotesRvAdapter.IDeleteItem {

    lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRvAdapter(this,this)
        recyclerView.adapter = adapter

        //observing the List
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->

            //here list can be null so we are checking
            //agar list null nahi ha tabhe update karna ha
            list?.let {
                adapter.update(it)
            }
        })
        //ViewModelProvider.AndroidViewModelFactory.getInstance(Application())).get(NotesViewModel)


    }
//our activity will only talk to viewModel so it will delete fun of view Model
    override fun onItemDeleted(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this,"${notes.text} Deleted" , Toast.LENGTH_SHORT).show()
    }

    fun submitBtn(view: View) {

        val addText = findViewById<EditText>(R.id.etText)
        val text = addText.text.toString()
        if(text.isNotEmpty()){
            viewModel.insertNote(Notes(text))
            Toast.makeText(this,"$text Inserted" , Toast.LENGTH_SHORT).show()
            closeKeyBoard()

        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}