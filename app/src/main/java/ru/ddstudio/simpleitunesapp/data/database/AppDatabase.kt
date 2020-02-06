package ru.ddstudio.simpleitunesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.ddstudio.simpleitunesapp.data.database.converters.DateConverter
import ru.ddstudio.simpleitunesapp.data.database.daos.AlbumDao
import ru.ddstudio.simpleitunesapp.data.database.daos.SongDao


@Database(
    entities = [Album::class, Song::class], version = 1, exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getAlbumDao() : AlbumDao
    abstract fun getSongDao(): SongDao

    companion object{
        @Volatile private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "database"
            ).build()

    }
}
