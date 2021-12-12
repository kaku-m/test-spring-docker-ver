package com.example.jpa.repository

import com.example.jpa.entity.Image
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Query

@Repository
interface ImageRepository: CrudRepository<Image, Int> {

    fun findByPageIdIs(pageId: Int): Iterable<Image>

}