package com.example.jpa.repository

import com.example.jpa.entity.ImageEntity
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@Repository
interface ImageRepository: CrudRepository<ImageEntity, Int> {

    @Query("SELECT i FROM ImageEntity AS i WHERE page_id = :pageId")
    fun findImages(
        @Param("pageId") pageId: Int,
    ): Iterable<ImageEntity>

}