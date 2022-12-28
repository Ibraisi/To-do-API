package com.example.todo.repository

import com.example.todo.model.ToDo
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoRepo: JpaRepository<ToDo,Long> {
    fun findByCompleted(completed:Boolean): List<ToDo>
}