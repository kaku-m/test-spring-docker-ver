package com.example.jpa.repository

import com.example.jpa.entity.Image
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Query

@Repository
interface ImageRepository: CrudRepository<Image, Int> {

    @Query("SELECT i FROM Image AS i WHERE page_id = :pageId")
    fun findImages(
        @Param("pageId") pageId: Int,
    ): Iterable<Image>

}