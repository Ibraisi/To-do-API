package com.example.todo.Service

import com.example.todo.model.ToDo
import com.example.todo.repository.ToDoRepo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

// This is a test class for the ToDoService in a Spring Boot application.
// The @SpringBootTest annotation is used to configure the Spring context for the test and
// the @AutoConfigureMockMvc annotation is used to configure the MockMvc instance for the test.
@SpringBootTest
@AutoConfigureMockMvc
class ToDoServiceTest {
    // The ToDoService instance is autowired into the test class, allowing us to test its behavior.
    @Autowired
    lateinit var toDoService: ToDoService

    // The toDoRepository property is mocked using the @MockBean annotation, allowing us to stub its behavior
    // for the purpose of the test.
    @MockBean
    lateinit var toDoRepository: ToDoRepo

    // The getToDosShouldReturnTodos function is a test case that tests the behavior of the ToDoService.getToDos
    // function. It creates a list of ToDo objects, stubs the behavior of the toDoRepository.findAll function
    // to return the list of ToDo objects, and verifies that the ToDoService.getToDos function returns a list
    // of ToDoDto objects with the same size as the list of ToDo objects.
    @Test
    fun getToDosShouldReturnTodos() {
        // Create a list of ToDo objects using the apply function.
        val todos = listOf(ToDo().apply {
            id = 1L
            name = "write unit tests"
            completed = false
        })

        // Stub the behavior of the toDoRepository.findAll function to return the list of ToDo objects.
        `when`(toDoRepository.findAll()).thenReturn(todos)

        // Call the ToDoService.getToDos function and store the result in a variable.
        val todoDtoList = toDoService.getTodos()

        // Use the assertThat function from the AssertJ library to verify that the size of the list of ToDoDto objects
        // is equal to 1.
        assertThat(todoDtoList).hasSize(1)
    }
        }

