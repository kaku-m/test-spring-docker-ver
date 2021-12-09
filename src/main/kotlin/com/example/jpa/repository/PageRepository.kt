package com.example.jpa.repository

import com.example.jpa.entity.PageEntity
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

@Repository
interface PageRepository: CrudRepository<PageEntity, Int> {

    @Query(
        """
        SELECT p1 FROM PageEntity AS p1 LEFT OUTER JOIN PageEntity AS p2
        ON p2.path = (SELECT MAX(path) FROM PageEntity WHERE p1.path LIKE CONCAT(path, '_%'))
        WHERE p2.id = :id
        """
    )
    fun findChildren(
        @Param("id") id: Int,
    ): Iterable<PageEntity>

    @Query(
        """
        SELECT p1 FROM PageEntity AS p1 LEFT OUTER JOIN PageEntity AS p2
        ON p1.path = (SELECT MAX(path) FROM PageEntity WHERE p2.path LIKE CONCAT(path, '_%'))
        WHERE p2.id = :id
        """
    )
    fun findParent(
        @Param("id") id: Int,
    ): Optional<PageEntity>

    @Query(
        """
        SELECT p FROM PageEntity AS p
        WHERE (SELECT path FROM PageEntity WHERE id = :id) LIKE CONCAT(p.path, '_%')
        """
    )
    fun findAncestors(
        @Param("id") id: Int,
    ): Iterable<PageEntity>

    @Modifying
    @Query("UPDATE PageEntity SET title = :title, content = :content WHERE id = :id")
    fun update(
        @Param("id") id: Int,
        @Param("title") title: String,
        @Param("content") content: String
    )

    @Modifying
    @Query("DELETE FROM PageEntity WHERE path LIKE CONCAT('%/', :id, '/%')")
    fun delete(
        @Param("id") id: Int
    )

}