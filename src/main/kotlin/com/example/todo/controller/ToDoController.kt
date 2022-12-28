package com.example.todo.controller

import com.example.todo.Service.ToDoService
import com.example.todo.dto.CreateToDoDto
import com.example.todo.dto.ToDoDto
import com.example.todo.dto.UpdateToDoDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/todos")
class ToDoController(val toDoService:ToDoService) {

    @PostMapping("")
    fun createToDo(@RequestBody newTodo:CreateToDoDto): ResponseEntity<ToDoDto>{
        val toDoDto = toDoService.createToDo(newTodo)
        return ResponseEntity(toDoDto,HttpStatus.CREATED)
    }
    @GetMapping("")
    fun getToDos(@RequestParam completed:Optional<Boolean>) : List<ToDoDto>{
        if(completed.isPresent){
            return toDoService.getTodos(completed.get())
        }
        return toDoService.getTodos()
    }
    @GetMapping("/{id}")
    fun getToDo(@PathVariable id:Long):ToDoDto{
        return toDoService.getToDoById(id)
    }
    @PutMapping("/{id}")
    fun updateToDo(@PathVariable id:Long,@RequestBody updateToDoDto: UpdateToDoDto ):ToDoDto{
        return toDoService.updateToDo(id,updateToDoDto)
    }
    @DeleteMapping("/{id}")
    fun deleteToDo(@PathVariable id:Long):ResponseEntity<Any>{
        toDoService.deleteToDo(id)
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/deleteAllToDos")
    fun deleteAll(){
        toDoService.deleteAll()
    }
}