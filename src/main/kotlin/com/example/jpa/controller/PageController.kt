package com.example.jpa.controller

import com.example.jpa.service.PageService
import com.example.jpa.entity.PageEntity
import com.example.jpa.entity.SequenceEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequestMapping("/api/page")
class PageController(private val pageService: PageService) {

    @GetMapping("/list")
    fun list(): Iterable<PageEntity> {
        return pageService.list()
    }

    @PostMapping("/create/new")
    fun createNew(): SequenceEntity {
        return pageService.createNew()
    }

}