package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//table name
@Entity(tableName = "notes_table")
class Notes(

    //there is only 1 thing in table the note type string
    @ColumnInfo(name = "text") val text: String
) {
    //id is passed outside the constructor because we dont want to pass it again and again
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}