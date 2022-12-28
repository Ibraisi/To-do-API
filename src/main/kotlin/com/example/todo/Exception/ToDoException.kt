package com.example.todo.Exception

class ToDoException(status: Int, message: String): RuntimeException(message) {
    val status: Int = status
}

