package com.example.jpa.repository

import com.example.jpa.entity.Sequence
import org.springframework.data.repository.CrudRepository

interface SequenceRepository: CrudRepository<Sequence, Int>