package com.pop.apptest.pr

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Fail(val failText : String): Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Fail -> "Fail[text=$failText]"
            is Error -> "Error[exception=$exception]"
        }
    }
}