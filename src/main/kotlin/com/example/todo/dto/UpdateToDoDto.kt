package com.example.todo.dto

import lombok.Data

@Data
data class UpdateToDoDto(
    val name:String,
    val completed:Boolean
)
