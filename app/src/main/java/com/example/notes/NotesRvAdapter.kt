package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRvAdapter(private val context: Context, private val listener: IDeleteItem) : RecyclerView.Adapter<NotesRvAdapter.NotesViewHolder>() {

    val allNotes = ArrayList<Notes>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.tvText)
        val btnDelete = itemView.findViewById<ImageView>(R.id.ivDeleteImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        //passing through a viewHolder Instance cause we will handel clicks when we are creating
        val viewHolder = NotesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_notes, parent, false)
        )

        //passing listener in constructor
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemDeleted(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = allNotes[position]
        holder.textView.text = current.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun update(newList : List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

    //to delete the note activity must be responsible so we are making it an Interface
    interface IDeleteItem {
        fun onItemDeleted(notes: Notes)
    }
}