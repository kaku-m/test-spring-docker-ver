package com.example.jpa.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "users")
class UserEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(name = "name", length = 255, nullable = false)
    var name: String? = null

    @Column(name = "password", length = 255, nullable = false)
    var password: String? = null

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    var createdAt: Timestamp? = null

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    var updatedAt: Timestamp? = null

}