package ru.ddstudio.simpleitunesapp.data.remote

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response
import ru.ddstudio.simpleitunesapp.data.Result
import ru.ddstudio.simpleitunesapp.data.database.Song

/**
 * Абстрактный класс Data Source для получения данных от сетевеого запроса
 */

abstract class BaseDataSource{
    suspend fun <T> getResult(call : suspend () -> Response<T>) : Result<T>{
        return try {
            //Получаем ответ от вызова
            val response = call()
            if(response.isSuccessful) {
                //тело
                val body = response.body()
                if (body != null)
                    return Result.success(body)
            }
            error("${response.code()} ${response.message()} ${response.errorBody()}")
        } catch (e: Exception){
            Log.d("BaseDataSource", e.toString())
            Result.error(e.message ?: e.toString())
        }
    }

    suspend fun getResultSong(call : suspend () -> Response<BaseResponse<JsonObject>>): Result<BaseResponse<Song>>{
        try {
            //Получаем ответ от вызова
            val response = call()
            if(response.isSuccessful) {
                //тело
                val body = response.body()
                if (body != null){
                    val gson = Gson()
                    val responseMap = response.body()?.results?.groupBy { it["wrapperType"].asString } ?: emptyMap()
                    // лист объектов где wrapperType == "track"
                    val trackList = responseMap["track"]?.map { gson.fromJson(it.toString(), Song::class.java) } ?: emptyList()
                    return Result.success(BaseResponse(body.resultCount, trackList))

                }

            }
            error("${response.code()} ${response.message()} ${response.errorBody()}")
        }
        catch (e: Exception){
            Log.d("BaseDataSource", e.toString())
            return Result.error(e.message ?: e.toString())
        }
    }

}