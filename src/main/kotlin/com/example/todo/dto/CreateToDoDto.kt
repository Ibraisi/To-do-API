package com.example.todo.dto

import lombok.Data

@Data
data class CreateToDoDto(
    val name: String,
    val completed: Boolean
)
