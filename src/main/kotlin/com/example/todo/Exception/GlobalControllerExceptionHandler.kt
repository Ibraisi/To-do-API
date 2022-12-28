package com.example.todo.Exception

import com.example.todo.dto.ErrorDto
import lombok.extern.log4j.Log4j2
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice(annotations = [RestController::class])
@Log4j2
class GlobalControllerExceptionHandler {
    private val log = LoggerFactory.getLogger(GlobalControllerExceptionHandler::class.java)

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ErrorDto> {
        log.error("response status exception", ex)
        val errorDto = ErrorDto(ex.message)
        return ResponseEntity(errorDto, ex.statusCode)
    }

    @ExceptionHandler(ToDoException::class)
    fun handleToDoException(ex: ToDoException): ResponseEntity<ErrorDto> {
        log.error("todo custom exception", ex)
        val errorDto = ErrorDto(ex.message)
        val httpStatus = HttpStatus.resolve(ex.status)
        return ResponseEntity(errorDto, httpStatus!!)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorDto> {
        log.error("internal server error", ex)
        val errorDto = ErrorDto("internal server error")
        return ResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}