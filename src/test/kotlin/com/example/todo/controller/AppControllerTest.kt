package com.example.todo.controller

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

// This is a test class for the AppController in a Spring Boot application.
// The @SpringBootTest annotation is used to configure the Spring context for the test and
// the @AutoConfigureMockMvc annotation is used to configure the MockMvc instance for the test.
@SpringBootTest
@AutoConfigureMockMvc
// The MockMvc instance is autowired into the test class, allowing us to use it to send HTTP requests
// to the application and verify the responses.
class AppControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    // The getRoot function is a test case that sends an HTTP GET request to the root URL of the application
    // and verifies that the response has an HTTP status of 200 (OK) and contains the string "Hello World".
    @Test
    fun getRoot() {
        mockMvc.perform(get("/")).andExpect(status().isOk).andExpect(content().string(containsString("Hello World")))
    }
}
