package com.example.canciones.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "song_table")
data class Song (
    @PrimaryKey
    val title: String,
    val author: String,
    val album : String,
    val year: Int


)