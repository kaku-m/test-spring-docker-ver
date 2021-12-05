package com.example.jpa.controller

import com.example.jpa.entity.UserEntity
import com.example.jpa.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["/demo"])
class UserController {
    @Autowired
    private val userRepository: UserRepository? = null

    @PostMapping(path = ["/add"])
    @ResponseBody
    fun addNewUser(
        @RequestParam name: String?,
        @RequestParam password: String?
    ): String {
        val user = UserEntity()
        user.name = name
        user.password = password
        userRepository!!.save<UserEntity>(user)
        return "Saved22"
    }

    @ResponseBody
    @GetMapping(path = ["/all"])
    fun allUser(): Iterable<UserEntity?> {
        return userRepository!!.findAll()
    }

}