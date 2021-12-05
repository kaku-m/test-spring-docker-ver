package com.example.jpa.repository

import com.example.jpa.entity.SequenceEntity
import org.springframework.data.repository.CrudRepository

interface SequenceRepository: CrudRepository<SequenceEntity, Int>