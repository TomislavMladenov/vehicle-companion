package com.example.vehiclecompanion.presentation.base

sealed class SimpleViewState<out T> {
    data object Loading : SimpleViewState<Nothing>()
    data class Data<out T>(val value: T): SimpleViewState<T>()
    data class Error(val message: String): SimpleViewState<Nothing>()
}