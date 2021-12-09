package com.example.jpa.service

import com.example.jpa.repository.PageRepository
import com.example.jpa.repository.SequenceRepository
import com.example.jpa.entity.PageEntity
import com.example.jpa.entity.SequenceEntity
import java.util.Optional
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PageService(
    private val pageRepository: PageRepository,
    private val sequenceRepository: SequenceRepository
) {

    fun create(parentPageId: Int?, title: String): PageEntity {
        return if (parentPageId == null) createNew(title) else createChild(parentPageId, title)
    }

    private fun createNew(title: String): PageEntity {
        val sequence: SequenceEntity = sequenceRepository.save(SequenceEntity())
        val page = PageEntity()
        page.id = sequence.id
        page.path = "/${page.id}/"
        page.title = title
        return pageRepository.save(page)
    }

    private fun createChild(parentPageId: Int, title: String): PageEntity {
        val parentPageFindResult: Optional<PageEntity> = pageRepository.findById(parentPageId)
        val parentPage: PageEntity = parentPageFindResult.get()
        // TODO 親ページ存在チェック
        // TODO タイトル重複チェック
        val sequence: SequenceEntity = sequenceRepository.save(SequenceEntity())
        val page = PageEntity()
        page.id = sequence.id
        page.path = "${parentPage.path}${page.id}/"
        page.title = title
        return pageRepository.save(page)
    }

    fun findAll(): Iterable<PageEntity> {
        return pageRepository.findAll()
    }

    fun findById(id: Int): Optional<PageEntity> {
        return pageRepository.findById(id)
    }

    fun findChildren(id: Int): Iterable<PageEntity> {
        return pageRepository.findChildren(id)
    }

    fun findParent(id: Int): Optional<PageEntity> {
        return pageRepository.findParent(id)
    }

    fun findAncestors(id: Int): Iterable<PageEntity> {
        return pageRepository.findAncestors(id)
    }

    @Transactional
    fun update(id: Int, title: String, content: String) {
        // TODO タイトル重複チェック（当該ページ以外で）
        return pageRepository.update(id, title, content)
    }

    @Transactional
    fun delete(id: Int) {
        return pageRepository.delete(id)
    }

}