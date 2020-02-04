package ru.ddstudio.simpleitunesapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.ddstudio.simpleitunesapp.data.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule{
    @Provides
    @Singleton
    fun provideDatabase(context: Context) = AppDatabase.getInstance(context)
}