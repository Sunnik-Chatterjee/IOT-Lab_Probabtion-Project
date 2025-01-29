package com.example.project1.state

sealed interface UIstate<out T> {
    object Idle:UIstate<Nothing>
    object Loading:UIstate<Nothing>
    class Success<T>(val data: T?):UIstate<T>
    class Error(val message: String):UIstate<Nothing>
}