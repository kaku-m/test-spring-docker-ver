package com.example.jpa.repository

import com.example.jpa.entity.Page
import java.util.Optional
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.Modifying

@Repository
interface PageRepository: CrudRepository<Page, Int> {

    @Query(
        """
        SELECT p1 FROM Page AS p1 LEFT OUTER JOIN Page AS p2
        ON p2.path = (SELECT MAX(path) FROM Page WHERE p1.path LIKE CONCAT(path, '_%'))
        WHERE p2.id = :id
        """
    )
    fun findChildren(
        @Param("id") id: Int,
    ): Iterable<Page>

    @Query(
        """
        SELECT p1 FROM Page AS p1 LEFT OUTER JOIN Page AS p2
        ON p1.path = (SELECT MAX(path) FROM Page WHERE p2.path LIKE CONCAT(path, '_%'))
        WHERE p2.id = :id
        """
    )
    fun findParent(
        @Param("id") id: Int,
    ): Optional<Page>

    @Query("SELECT p FROM Page AS p WHERE (SELECT path FROM Page WHERE id = :id) LIKE CONCAT(p.path, '_%')")
    fun findAncestors(
        @Param("id") id: Int,
    ): Iterable<Page>

    @Modifying
    @Query("UPDATE Page SET title = :title, content = :content WHERE id = :id")
    fun update(
        @Param("id") id: Int,
        @Param("title") title: String,
        @Param("content") content: String
    ): Int
    // 戻り値はIntを指定すると更新件数

    @Modifying
    @Query(
        """
        UPDATE Page AS p SET path = CONCAT(:parentPagePath, SUBSTRING(p.path, :position))
        WHERE p.path LIKE CONCAT(:path, '%')
        """
    )
    fun move(
        @Param("parentPagePath") parentPagePath: String,
        @Param("path") path: String,
        @Param("position") position: Int
    ): Int
    // 戻り値はIntを指定すると更新件数

    @Modifying
    @Query("DELETE FROM Page WHERE path LIKE CONCAT('%/', :id, '/%')")
    fun delete(
        @Param("id") id: Int
    ): Int
    // 戻り値はIntを指定すると削除件数

}