package com.example.todo.controller

import com.example.todo.Service.ToDoService
import com.example.todo.dto.ToDoDto
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

// This is a test class for the ToDoController in a Spring Boot application.
// The @SpringBootTest annotation is used to configure the Spring context for the test and
// the @AutoConfigureMockMvc annotation is used to configure the MockMvc instance for the test.
@SpringBootTest
@AutoConfigureMockMvc
// The MockMvc instance is autowired into the test class, allowing us to use it to send HTTP requests
// to the application and verify the responses.
class ToDoControllerTest {

    // The toDoService property is mocked using the @MockBean annotation, allowing us to stub its behavior
    // for the purpose of the test.
    @Autowired
    lateinit var mockMvc: MockMvc
    @MockBean
    lateinit var toDoService: ToDoService

    // The getToDosShouldReturnTodos function is a test case that sends an HTTP GET request to the /todos URL
    // of the application, and verifies that the response has an HTTP status of 200 (OK), contains a JSON array
    // with one element, and the first element has a "name" property with the value "write unit tests".
    @Test
    fun getToDosShouldReturnTodos() {
        // Create a list of ToDoDto objects and a ToDoDto object for the test.
        val todos : List<ToDoDto> = listOf(ToDoDto(1L, "write unit tests", false))
        val todoDto = todos[0]

        // Stub the behavior of the toDoService.getToDos function to return the list of ToDoDto objects.
        `when`(toDoService.getTodos()).thenReturn(todos)

        // Send an HTTP GET request to the /todos URL and verify the response.
        mockMvc.perform(get("/todos")).andExpect(status().isOk)
            .andExpect(jsonPath("$", hasSize<Any>(1)))
            .andExpect(jsonPath("$[0].name", `is`(todoDto.name)))
    }
}
