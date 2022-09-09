package com.example.task7.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task7.domain.repositories.MainRepository
import com.example.task7.model.CoursesResponse
import com.example.task7.network.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _responseState = MutableStateFlow<ResponseHandler<CoursesResponse>>(ResponseHandler.Loading())
    val responseState: StateFlow<ResponseHandler<CoursesResponse>> get() = _responseState

    fun getCourses() {
        viewModelScope.launch {
            mainRepository.getCourses()
            .collect {
                when (it) {
                    is ResponseHandler.Success -> _responseState.emit(it)
                    is ResponseHandler.Error -> _responseState.emit(it)
                    is ResponseHandler.Loading -> _responseState.emit(it)
                }
            }
        }
    }
}