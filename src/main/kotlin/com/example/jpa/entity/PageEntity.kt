package com.example.jpa.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column

@Entity
@Table(name = "pages")
class PageEntity {

    @Id
    @Column(name = "id", nullable = false)
    val id: Int? = null

    @Column(name = "path", nullable = false)
    var path: String? = null

    @Column(name = "title", length = 255, nullable = false)
    var title: String? = null

    @Column(name = "content")
    var content: String? = null

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    val createdAt: Timestamp? = null

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    val updatedAt: Timestamp? = null

}