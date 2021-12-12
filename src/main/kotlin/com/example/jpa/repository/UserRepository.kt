package com.example.jpa.repository

import com.example.jpa.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int>