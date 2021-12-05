package com.example.jpa.repository

import com.example.jpa.entity.PageEntity
import org.springframework.data.repository.CrudRepository

interface PageRepository: CrudRepository<PageEntity, Int>