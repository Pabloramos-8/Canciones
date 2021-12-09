package com.example.canciones.db;

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(song : Song)

    @Query("DELETE FROM song_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM song_table ORDER BY year ASC")
    fun getAllSongs() : LiveData<List<Song>>



}
