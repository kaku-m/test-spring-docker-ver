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
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/api/pages")
class PageController(private val pageService: PageService) {

    @PostMapping
    fun create(
        @RequestParam(required = false) parentPageId: Int?,
        @RequestParam title: String,
        @RequestParam(required = false) content: String?
    ): Page {
        return pageService.create(parentPageId, title, content)
    }

    @GetMapping
    fun getAll(): Iterable<Page> {
        return pageService.getAll()
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Int
    ): Optional<Page> {
        return pageService.get(id)
    }

    @GetMapping("/{id}/children")
    fun getChildren(
        @PathVariable id: Int
    ): Iterable<Page> {
        return pageService.getChildren(id)
    }

    @GetMapping("/{id}/parent")
    fun getParent(
        @PathVariable id: Int
    ): Optional<Page> {
        return pageService.getParent(id)
    }

    @GetMapping("/{id}/ancestors")
    fun getAncestors(
        @PathVariable id: Int
    ): Iterable<Page> {
        return pageService.getAncestors(id)
    }

    @PatchMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @RequestParam title: String,
        @RequestParam content: String
    ): Page {
        return pageService.update(id, title, content)
        // TODO 戻り値をどうするか
    }

    @PatchMapping("/{id}/path")
    fun updatePath(
        @PathVariable id: Int,
        @RequestParam parentPageId: Int
    ): String {
        return pageService.updatePath(id, parentPageId)
        // TODO 戻り値をどうするか
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Int
    ): Int {
        return pageService.delete(id)
        // TODO 戻り値をどうするか
    }

    @PostMapping("/{id}/images")
    fun createImage(
        @PathVariable(name = "id") pageId: Int,
        @RequestParam name: String,
        @RequestParam path: String
    ): Image {
        return pageService.createImage(pageId, name, path)
    }

    @GetMapping("/{id}/images")
    fun getImages(
        @PathVariable(name = "id") pageId: Int,
    ): Iterable<Image> {
        return pageService.getImages(pageId)
    }

}