package com.example.jpa.service

import com.example.jpa.repository.UserRepository
import com.example.jpa.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun register(name: String, password: String): User {
        val user = User(
            name = name,
            password = password
        )
        return userRepository.save(user)
    }

}