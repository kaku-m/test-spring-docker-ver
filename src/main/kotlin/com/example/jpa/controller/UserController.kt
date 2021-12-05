package com.example.jpa.controller

import com.example.jpa.entity.UserEntity
import com.example.jpa.repository.UserRepository
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {

    @PostMapping("/add")
    fun addUser(
        @RequestParam name: String?,
        @RequestParam password: String?
    ): UserEntity {
        val user = UserEntity()
        user.name = name
        user.password = password
        return userRepository.save(user)
    }

    @GetMapping("/all")
    fun allUser(): Iterable<UserEntity> {
        return userRepository.findAll()
    }

}