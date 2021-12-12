package com.example.jpa.controller

import com.example.jpa.entity.Image
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
@RequestMapping("/api/pages")
class PageController(private val pageService: PageService) {

    @PostMapping("/create")
    fun create(
        @RequestParam(required = false) parentPageId: Int?,
        @RequestParam title: String
    ): PageEntity {
        return pageService.create(parentPageId, title)
    }

    @GetMapping("")
    fun findAll(): Iterable<PageEntity> {
        return pageService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Int
    ): Optional<PageEntity> {
        return pageService.findById(id)
    }

    @GetMapping("/{id}/children")
    fun findChildren(
        @PathVariable id: Int
    ): Iterable<PageEntity> {
        return pageService.findChildren(id)
    }

    @GetMapping("/{id}/parent")
    fun findParent(
        @PathVariable id: Int
    ): Optional<PageEntity> {
        return pageService.findParent(id)
    }

    @GetMapping("/{id}/ancestors")
    fun findAncestors(
        @PathVariable id: Int
    ): Iterable<PageEntity> {
        return pageService.findAncestors(id)
    }

    @PutMapping("/{id}/update")
    fun update(
        @PathVariable id: Int,
        @RequestParam title: String,
        @RequestParam content: String
    ): Int {
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