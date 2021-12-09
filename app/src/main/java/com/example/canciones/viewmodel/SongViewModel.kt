package com.example.canciones.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.canciones.db.Song
import com.example.canciones.db.SongDatabase
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {
    private val db: SongDatabase = SongDatabase.getInstance(application)

    val mAllSongs: LiveData<List<Song>> = db.songDao().getAllSongs()

    var displayText : String =""


    private fun insert(song: Song) {
        viewModelScope.launch {
            db.songDao().insert(song)
        }
    }

    private fun deleteAll() {
        viewModelScope.launch {
            db.songDao().deleteAll()
        }

    }

    fun addNewSong(title: String, author: String, album: String, year: String) {
        val newSong = Song(title, author, album, year.toInt())
        insert(newSong)
    }

    fun deleteAllSongs() {
        deleteAll()
    }

}