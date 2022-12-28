package com.example.todo.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/")
    fun hello():String {
        return "Hello World"
    }

}