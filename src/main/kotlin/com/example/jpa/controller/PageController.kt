package com.example.jpa.controller

import com.example.jpa.service.PageService
import com.example.jpa.entity.PageEntity
import java.util.Optional
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/api/page")
class PageController(private val pageService: PageService) {

    @PostMapping("/create")
    fun create(
        @RequestParam(name = "parentPageId", required = false) parentPageId: Int?,
        @RequestParam(name = "title") title: String
    ): PageEntity {
        return pageService.create(parentPageId, title)
    }

    @GetMapping("/list")
    fun findAll(): Iterable<PageEntity> {
        return pageService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable(name = "id") id: Int
    ): Optional<PageEntity> {
        return pageService.findById(id)
    }

    @GetMapping("/{id}/children")
    fun findChildren(
        @PathVariable(name = "id") id: Int
    ): Iterable<PageEntity> {
        return pageService.findChildren(id)
    }

    @GetMapping("/{id}/parent")
    fun findParent(
        @PathVariable(name = "id") id: Int
    ): Optional<PageEntity> {
        return pageService.findParent(id)
    }

    @GetMapping("/{id}/ancestors")
    fun findAncestors(
        @PathVariable(name = "id") id: Int
    ): Iterable<PageEntity> {
        return pageService.findAncestors(id)
    }

    @PutMapping("/{id}/update")
    fun update(
        @PathVariable(name = "id") id: Int,
        @RequestParam(name = "title") title: String,
        @RequestParam(name = "content") content: String
    ) {
        return pageService.update(id, title, content)
        // TODO 戻り値をどうするか
    }

    @DeleteMapping("/{id}/delete")
    fun delete(
        @PathVariable(name = "id") id: Int
    ) {
        return pageService.delete(id)
        // TODO 戻り値をどうするか
    }

}