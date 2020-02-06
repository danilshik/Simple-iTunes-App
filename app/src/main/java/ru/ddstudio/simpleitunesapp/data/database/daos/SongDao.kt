package ru.ddstudio.simpleitunesapp.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ddstudio.simpleitunesapp.data.database.Song

@Dao
interface SongDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item : List<Song>)

    @Query("SELECT * FROM song WHERE collection_id = :collectionId ORDER BY track_name")
    fun getSongByCollectionId(collectionId: Long): LiveData<List<Song>>
}