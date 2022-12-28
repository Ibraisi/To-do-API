package com.example.todo.Service

import com.example.todo.Exception.ToDoException
import com.example.todo.dto.CreateToDoDto
import com.example.todo.dto.ToDoDto
import com.example.todo.dto.UpdateToDoDto
import com.example.todo.model.ToDo
import com.example.todo.repository.ToDoRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.RuntimeException
import java.util.Optional

@Service
class ToDoService(val toDoRepo : ToDoRepo) {

    fun createToDo(createToDo: CreateToDoDto) : ToDoDto {
        val newTodo = ToDo()
        newTodo.name = createToDo.name
        newTodo.completed = createToDo.completed
        val toDo : ToDo = toDoRepo.save(newTodo)
        return ToDoDto(toDo)
    }
    fun getTodos() : List<ToDoDto>{
        val todos : List<ToDo> = toDoRepo.findAll()
        return todos.stream().map { e-> ToDoDto(e) }.toList()
    }
    fun getTodos(completed:Boolean) : List<ToDoDto>{
        val todos : List<ToDo>  = toDoRepo.findByCompleted(completed)
        return  todos.stream().map { e-> ToDoDto(e) }.toList()
    }
    fun getToDoById(id:Long)  : ToDoDto{
        val todo : Optional<ToDo> = toDoRepo.findById(id)
        if(todo.isPresent){
            return ToDoDto(todo.get())
        }else{
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"id was not found")
        }
    }
    fun updateToDo(id:Long, updatedToDo : UpdateToDoDto) : ToDoDto {
        val todo: Optional<ToDo> = toDoRepo.findById(id)
        if (todo.isPresent) {
            todo.get().name = updatedToDo.name
            todo.get().completed = updatedToDo.completed
            toDoRepo.save(todo.get())
            return ToDoDto(todo.get())
        } else {
            throw ToDoException(404, "updated to do not found!")
        }
    }
        fun deleteToDo(id:Long){
            val todo : Optional<ToDo> = toDoRepo.findById(id)
            if(todo.isPresent){
                toDoRepo.delete(todo.get())
            }else{
                throw RuntimeException("Deleted toDo not found")
            }
        }
    fun deleteAll(){
        toDoRepo.deleteAll()
    }
}