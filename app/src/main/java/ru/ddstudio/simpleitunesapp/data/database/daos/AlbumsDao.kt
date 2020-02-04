package ru.ddstudio.simpleitunesapp.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ddstudio.simpleitunesapp.data.database.Album

@Dao
interface AlbumDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums : List<Album>)

    @Query("SELECT * FROM album WHERE collection_name LIKE :searchQuery ORDER BY collection_name")
    fun getAlbumsBySearch(searchQuery : String): LiveData<List<Album>>

    @Query("SELECT * FROM album")
    fun getAlbums(): LiveData<List<Album>>
}