package com.example.jpa.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column

@Entity
@Table(name = "pages")
class Page {

    @Id
    @Column(name = "id", nullable = false)
    var id: Int = 0

    @Column(name = "path", nullable = false)
    var path: String = ""

    @Column(name = "title", nullable = false, unique = true, length = 255)
    var title: String = ""

    @Column(name = "content")
    var content: String? = null

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    val createdAt: Timestamp? = null

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    val updatedAt: Timestamp? = null

}