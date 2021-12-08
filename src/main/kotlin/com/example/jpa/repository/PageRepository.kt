package com.example.jpa.repository

import com.example.jpa.entity.PageEntity
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

@Repository
interface PageRepository: CrudRepository<PageEntity, Int> {

    @Modifying
    @Query("UPDATE PageEntity SET title = :title, content = :content WHERE id = :id")
    fun update(
        @Param("id") pageId: Int,
        @Param("title") pageTitle: String,
        @Param("content") content: String
    )

    @Modifying
    @Query("DELETE FROM PageEntity WHERE path LIKE :path")
    fun delete(
        @Param("path") path: String
    )

}