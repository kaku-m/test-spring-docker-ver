package com.example.jpa.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column

@Entity
@Table(name = "pages")
class Page(
    @Id
    @Column(nullable = false)
    val id: Int? = null,

    @Column
    val path: String,

    @Column
    var title: String,

    @Column
    var content: String? = null,

    @Column(name = "created_at", updatable = false)
    val createdAt: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(name = "updated_at")
    val updatedAt: Timestamp = Timestamp(System.currentTimeMillis())
)