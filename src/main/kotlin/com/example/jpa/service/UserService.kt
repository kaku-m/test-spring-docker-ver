package com.example.jpa.service

import com.example.jpa.repository.UserRepository
import com.example.jpa.entity.User
import com.example.jpa.util.TimestampUtil
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun create(name: String, password: String): User {
        val user = User(
            name = name,
            password = password,
            createdAt = TimestampUtil.getCurrentTimestamp(),
            updatedAt = TimestampUtil.getCurrentTimestamp()
        )
        return userRepository.save(user)
    }

}