package ru.ddstudio.simpleitunesapp.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import ru.ddstudio.simpleitunesapp.data.database.Album
import ru.ddstudio.simpleitunesapp.data.database.Song
import java.lang.reflect.Type

class InterfaceAdapter<T>: JsonDeserializer<T> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): T {
        val member = json as JsonObject

        val type = when(member["wrapperType"]?.asString){
            "collection" -> Album::class.java
            "track" -> Song::class.java
            else -> return context.deserialize(json, typeOfT)
        }


        return context.deserialize(json, type)
    }

}