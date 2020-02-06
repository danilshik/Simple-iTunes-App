package ru.ddstudio.simpleitunesapp.extensions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import ru.ddstudio.simpleitunesapp.data.Result

/**
 * Метод, который позволяет объединить принимаемые методы для
 * 1.Получения item из базы данных
 * 2. Получение item от REST-API
 * 3. Метод для сохранения в базу данных
 * И конвертацию в liveData
 */
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =

    liveData(Dispatchers.IO){
            emit(Result.loading())
            val responseSource = networkCall.invoke()
            Log.d("ResultLiveData Network", responseSource.toString())
            if(responseSource.status == Result.Status.SUCCESS) {
                saveCallResult(responseSource.data!!)
                Log.d("resultLiveData", "save database")
            }
            else if(responseSource.status == Result.Status.ERROR){
                emit(Result.error(responseSource.error))
                Log.d("resultLiveData", "error ${responseSource.error}")
            }
            val databaseSource = databaseQuery.invoke()
            emitSource(databaseSource.map { Result.success(it) })
        }

fun <T> mutableLiveData(defaultValue: T? = null): MutableLiveData<T>{
    val data = MutableLiveData<T>()

    if(defaultValue != null){
        data.value = defaultValue
    }

    return data
}


