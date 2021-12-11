package com.example.jpa.service

import com.example.jpa.repository.PageRepository
import com.example.jpa.repository.SequenceRepository
import com.example.jpa.repository.ImageRepository
import com.example.jpa.entity.PageEntity
import com.example.jpa.entity.SequenceEntity
import com.example.jpa.entity.ImageEntity
import java.util.Optional
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PageService(
    private val pageRepository: PageRepository,
    private val sequenceRepository: SequenceRepository,
    private val imageRepository: ImageRepository
) {

    @Transactional
    fun create(parentPageId: Int?, title: String): PageEntity {
        return if (parentPageId is Int) {
            createChild(parentPageId, title)
        } else {
            createNew(title)
        }
    }

    private fun createChild(parentPageId: Int, title: String): PageEntity {
        val parentPageFindResult: Optional<PageEntity> = pageRepository.findById(parentPageId)
        val parentPage: PageEntity = parentPageFindResult.get() // TODO NoSuchElementException
        // TODO 親ページ存在チェック
        // TODO タイトル重複チェック
        val sequence: SequenceEntity = sequenceRepository.save(SequenceEntity())
        val page = PageEntity()
        page.id = sequence.id
        page.path = "${parentPage.path}${page.id}/"
        page.title = title
        return pageRepository.save(page)
    }

    private fun createNew(title: String): PageEntity {
        val sequence: SequenceEntity = sequenceRepository.save(SequenceEntity())
        val page = PageEntity()
        page.id = sequence.id
        page.path = "/${page.id}/"
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
    fun update(id: Int, title: String, content: String): Int {
        // TODO タイトル重複チェック（当該ページ以外で）
        return pageRepository.update(id, title, content)
    }

    @Transactional
    fun move(id: Int, parentPageId: Int): String {
        if (id == parentPageId) {
            return "エラー（移動先が自分の下）"
        }
        var isDescendant = false
        val ancestorPages: Iterable<PageEntity> = pageRepository.findAncestors(parentPageId)
        ancestorPages.forEach {
            if (it.id == id) {
                isDescendant = true
            }
        }
        if (isDescendant) {
            return "エラー（移動先が自分の子孫の下）"
        }
        val parentPageFindResult: Optional<PageEntity> = pageRepository.findById(parentPageId)
        val parentPage: PageEntity = parentPageFindResult.get() // TODO NoSuchElementException
        val pageFindResult: Optional<PageEntity> = pageRepository.findById(id)
        val page: PageEntity = pageFindResult.get() // TODO NoSuchElementException
        val position: Int = page.path.indexOf("/${id}/") + 2
        val count: Int = pageRepository.move(parentPage.path, page.path, position)
        return "${count}件更新しました parentPage.path=${parentPage.path} page.path=${page.path} position=${position}"
    }

    @Transactional
    fun delete(id: Int): Int {
        return pageRepository.delete(id)
    }

    fun saveImage(pageId: Int, name: String, path: String): ImageEntity {
        // TODO ページ存在チェック
        val image = ImageEntity()
        image.pageId = pageId
        image.name = name
        image.path = path
        return imageRepository.save(image)
    }

    fun findImages(pageId: Int): Iterable<ImageEntity> {
        return imageRepository.findImages(pageId)
    }

}