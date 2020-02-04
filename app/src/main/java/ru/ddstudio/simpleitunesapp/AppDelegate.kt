package ru.ddstudio.simpleitunesapp

import android.app.Application
import ru.ddstudio.simpleitunesapp.di.components.AppComponent
import ru.ddstudio.simpleitunesapp.di.components.DaggerAppComponent
import ru.ddstudio.simpleitunesapp.di.modules.AppModule
import ru.ddstudio.simpleitunesapp.di.modules.DatabaseModule
import ru.ddstudio.simpleitunesapp.di.modules.NetworkModule

class AppDelegate : Application(){
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule())
            .build()
    }
}