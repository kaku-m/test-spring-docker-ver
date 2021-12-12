package com.example.jpa.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Int? = null,

    @Column
    val name: String,

    @Column
    val password: String,

    @Column(name = "created_at", updatable = false)
    val createdAt: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(name = "updated_at")
    val updatedAt: Timestamp = Timestamp(System.currentTimeMillis())
)