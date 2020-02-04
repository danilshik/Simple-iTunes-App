package ru.ddstudio.simpleitunesapp.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.Provides
import ru.ddstudio.simpleitunesapp.data.database.converters.DateConverter
import ru.ddstudio.simpleitunesapp.data.database.daos.AlbumDao
import javax.inject.Singleton


@Database(
    entities = [Album::class], version = 1, exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getAlbumDao() : AlbumDao

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
