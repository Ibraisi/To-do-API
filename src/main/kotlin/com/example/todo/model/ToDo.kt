package com.example.todo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "todos")
data class ToDo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,
    var completed: Boolean
) {
    constructor() : this(0,"",false)
}
