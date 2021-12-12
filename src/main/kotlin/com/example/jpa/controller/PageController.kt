package com.example.jpa.controller

import com.example.jpa.service.PageService
import com.example.jpa.entity.Page
import com.example.jpa.entity.Image
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
@RequestMapping("/api/pages")
class PageController(private val pageService: PageService) {

    @PostMapping("/create")
    fun create(
        @RequestParam(required = false) parentPageId: Int?,
        @RequestParam title: String,
        @RequestParam(required = false) content: String?
    ): Page {
        return pageService.create(parentPageId, title, content)
    }

    @GetMapping
    fun findAll(): Iterable<Page> {
        return pageService.findAll()
    }

    @GetMapping("/{id}")
    fun find(
        @PathVariable id: Int
    ): Optional<Page> {
        return pageService.find(id)
    }

    @GetMapping("/{id}/children")
    fun findChildren(
        @PathVariable id: Int
    ): Iterable<Page> {
        return pageService.findChildren(id)
    }

    @GetMapping("/{id}/parent")
    fun findParent(
        @PathVariable id: Int
    ): Optional<Page> {
        return pageService.findParent(id)
    }

    @GetMapping("/{id}/ancestors")
    fun findAncestors(
        @PathVariable id: Int
    ): Iterable<Page> {
        return pageService.findAncestors(id)
    }

    @PutMapping("/{id}/update")
    fun update(
        @PathVariable id: Int,
        @RequestParam title: String,
        @RequestParam content: String
    ): Page {
        return pageService.update(id, title, content)
        // TODO 戻り値をどうするか
    }

    @PutMapping("/{id}/move")
    fun move(
        @PathVariable id: Int,
        @RequestParam parentPageId: Int
    ): String {
        return pageService.move(id, parentPageId)
        // TODO 戻り値をどうするか
    }

    @DeleteMapping("/{id}/delete")
    fun delete(
        @PathVariable id: Int
    ): Int {
        return pageService.delete(id)
        // TODO 戻り値をどうするか
    }

    @PostMapping("/{id}/images/save")
    fun saveImage(
        @PathVariable(name = "id") pageId: Int,
        @RequestParam name: String,
        @RequestParam path: String
    ): Image {
        return pageService.saveImage(pageId, name, path)
    }

    @GetMapping("/{id}/images")
    fun findImages(
        @PathVariable(name = "id") pageId: Int,
    ): Iterable<Image> {
        return pageService.findImages(pageId)
    }

}