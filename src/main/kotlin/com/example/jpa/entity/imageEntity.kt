package com.example.jpa.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "images")
class ImageEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    @Column(name = "page_id", nullable = false)
    var pageId: Int = 0

    @Column(nullable = false, unique = true, length = 255)
    var name: String = ""

    @Column(nullable = false)
    var path: String = ""

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    val createdAt: Timestamp? = null

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    val updatedAt: Timestamp? = null

}