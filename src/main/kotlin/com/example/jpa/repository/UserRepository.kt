package com.example.jpa.repository

import com.example.jpa.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity?, Int?>