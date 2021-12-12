package com.example.jpa.controller

import com.example.jpa.service.UserService
import com.example.jpa.entity.User
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(
        @RequestParam name: String,
        @RequestParam password: String
    ): User {
        return userService.register(name, password)
    }

}