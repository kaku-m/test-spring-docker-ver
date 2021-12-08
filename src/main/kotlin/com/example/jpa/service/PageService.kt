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

    fun create(parentPageId: Int?, pageTitle: String): PageEntity {
        return if (parentPageId == null) createNew(pageTitle) else createChild(parentPageId, pageTitle)
    }

    private fun createNew(pageTitle: String): PageEntity {
        val sequence: SequenceEntity = sequenceRepository.save(SequenceEntity())
        val page = PageEntity()
        page.id = sequence.id
        page.path = "/${page.id}/"
        page.title = pageTitle
        return pageRepository.save(page)
    }

    private fun createChild(parentPageId: Int, pageTitle: String): PageEntity {
        val parentPageFindResult: Optional<PageEntity> = pageRepository.findById(parentPageId)
        val parentPage: PageEntity = parentPageFindResult.get()
        // TODO 親ページ存在チェック
        // TODO タイトル重複チェック
        val sequence: SequenceEntity = sequenceRepository.save(SequenceEntity())
        val page = PageEntity()
        page.id = sequence.id
        page.path = "${parentPage.path}${page.id}/"
        page.title = pageTitle
        return pageRepository.save(page)
    }

    fun findAll(): Iterable<PageEntity> {
        return pageRepository.findAll()
    }

    fun findById(id: Int): Optional<PageEntity> {
        return pageRepository.findById(id)
    }

    @Transactional
    fun update(pageId: Int, pageTitle: String, content: String) {
        // TODO タイトル重複チェック（当該ページ以外で）
        return pageRepository.update(pageId, pageTitle, content)
        // TODO 戻り値
    }

    @Transactional
    fun delete(id: Int) {
        val path = "%/${id}/%"
        return pageRepository.delete(path)
    }

}