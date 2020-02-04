package ru.ddstudio.simpleitunesapp.api

import android.util.Log
import retrofit2.Response
import ru.ddstudio.simpleitunesapp.data.Result

/**
 * Абстрактный класс Data Source c параметрами status, error и data
 */

abstract class BaseDataSource{
    suspend fun <T> getResult(call : suspend () -> Response<T>) : Result<T>{
        try {
            //Получаем ответ от вызова
            val response = call()
            if(response.isSuccessful) {
                //тело
                val body = response.body()
                if (body != null)
                    return Result.success(body)
            }
            return error("${response.code()} ${response.message()} ${response.errorBody()}")
        }
        catch (e: Exception){
            Log.d("BaseDataSource", e.toString())
            return Result.error(e.message ?: e.toString())
        }
    }
}