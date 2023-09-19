package com.bondstein.onlineattendanceapp.data

import android.util.Log
import com.bondstein.onlineattendanceapp.data.remote.api.APIService
import com.bondstein.onlineattendanceapp.utils.State
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: APIService) {

    fun callStoresApi(pageNumber: Int) = flow {
        emit(State.Loading)
        try {
            val result = apiService.callStoresApi(pageNumber)
            emit(State.Success(result))
        } catch (exception: Exception) {
            emit(State.Error(exception))
        }
    }
}