package com.example.todo.dto

import com.example.todo.model.ToDo
import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
data class ToDoDto(
     val id:Long,
     val name:String,
     val completed:Boolean
){
    constructor(entity: ToDo) : this(entity.id, entity.name, entity.completed)
}
