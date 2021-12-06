package com.example.jpa.controller

import com.example.jpa.service.PageService
import com.example.jpa.entity.PageEntity
import java.util.Optional
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping("/api/page")
class PageController(private val pageService: PageService) {

    @GetMapping("/list")
    fun findAll(): Iterable<PageEntity> {
        return pageService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Optional<PageEntity> {
        return pageService.findById(id)
    }

    @PostMapping("/create")
    fun create(
        @RequestParam parentPageId: Int?,
        @RequestParam pageTitle: String
    ): PageEntity {
        return pageService.create(parentPageId, pageTitle)
    }

}