package ru.ddstudio.simpleitunesapp.api

import retrofit2.Response
import ru.ddstudio.simpleitunesapp.data.Result

/**
 * Абстрактный класс Data Source c параметрами status, error и data
 */

abstract class BaseDataSource{
    public suspend fun <T> getResult(call : suspend () -> Response<T>) : Result<T>{
        try {
            //Получаем ответ от вызова
            val response = call()
            if(response.isSuccessful) {
                //тело
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error("${response.code()} ${response.message()} ${response.errorBody()}")
        }
        catch (e: Exception){
            return error(e.message ?: e.toString())
        }
    }
}