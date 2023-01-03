package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "title")  val title : String?,
    @ColumnInfo(name = "Note")   val note : String?,
    @ColumnInfo(name = "Date")   val date : String?

): Serializable
