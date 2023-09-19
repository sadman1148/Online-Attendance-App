package com.bondstein.onlineattendanceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondstein.onlineattendanceapp.data.Repository
import com.bondstein.onlineattendanceapp.data.remote.model.BaseModel
import com.bondstein.onlineattendanceapp.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val baseData: LiveData<State<BaseModel>> get() = _baseData
    private val _baseData: MutableLiveData<State<BaseModel>> = MutableLiveData()

    fun getBaseData(pageNumber: Int) {
        viewModelScope.launch {
            repository.callStoresApi(pageNumber).onEach {
                _baseData.value = it
            }.launchIn(viewModelScope)
        }
    }
}