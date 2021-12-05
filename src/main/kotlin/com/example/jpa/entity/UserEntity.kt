package com.example.jpa.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var name: String? = null
    var password: String? = null
    var created_at: Date? = null
    var updated_at: Date? = null
}