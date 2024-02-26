package com.example.rishabh_mobiquity_ta.common

sealed class ApiState <T>{
    class Loading<T> : ApiState<T>()
    data class Success<T> (val data: T) : ApiState<T>()
    data class Error<T> (val error: Any): ApiState<T>()
}