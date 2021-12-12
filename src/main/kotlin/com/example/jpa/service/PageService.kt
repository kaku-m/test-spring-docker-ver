package com.example.jpa.service

import com.example.jpa.repository.PageRepository
import com.example.jpa.repository.SequenceRepository
import com.example.jpa.repository.ImageRepository
import com.example.jpa.entity.Page
import com.example.jpa.entity.Sequence
import com.example.jpa.entity.Image
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
    fun create(parentPageId: Int?, title: String, content: String?): Page {
        return if (parentPageId is Int) {
            createChild(parentPageId, title, content)
        } else {
            createNew(title, content)
        }
    }

    private fun createChild(parentPageId: Int, title: String, content: String?): Page {
        val parentPageFindResult: Optional<Page> = pageRepository.findById(parentPageId)
        val parentPage: Page = parentPageFindResult.get() // TODO NoSuchElementException
        // TODO 親ページ存在チェック
        // TODO タイトル重複チェック
        val sequence: Sequence = sequenceRepository.save(Sequence())
        val page = Page(
            id = sequence.id,
            path = "${parentPage.path}${sequence.id}/",
            title = title,
            content = content
        )
        return pageRepository.save(page)
    }

    private fun createNew(title: String, content: String?): Page {
        val sequence: Sequence = sequenceRepository.save(Sequence())
        val page = Page(
            id = sequence.id,
            path = "/${sequence.id}/",
            title = title,
            content = content
        )
        return pageRepository.save(page)
    }

    fun findAll(): Iterable<Page> {
        return pageRepository.findAll()
    }

    fun find(id: Int): Optional<Page> {
        return pageRepository.findById(id)
    }

    fun findChildren(id: Int): Iterable<Page> {
        return pageRepository.findChildren(id)
    }

    fun findParent(id: Int): Optional<Page> {
        return pageRepository.findParent(id)
    }

    fun findAncestors(id: Int): Iterable<Page> {
        return pageRepository.findAncestors(id)
    }

    @Transactional
    fun update(id: Int, title: String, content: String): Page {
        // TODO タイトル重複チェック（当該ページ以外で）
        val pageFindResult: Optional<Page> = pageRepository.findById(id)
        val page: Page = pageFindResult.get() // TODO NoSuchElementException
        page.title = title
        page.content = content
        return pageRepository.save(page)
    }

    @Transactional
    fun move(id: Int, parentPageId: Int): String {
        if (id == parentPageId) {
            return "エラー（移動先が自分の下）"
        }
        var isDescendant = false
        val ancestorPages: Iterable<Page> = pageRepository.findAncestors(parentPageId)
        ancestorPages.forEach {
            if (it.id == id) {
                isDescendant = true
            }
        }
        if (isDescendant) {
            return "エラー（移動先が自分の子孫の下）"
        }
        val parentPageFindResult: Optional<Page> = pageRepository.findById(parentPageId)
        val parentPage: Page = parentPageFindResult.get() // TODO NoSuchElementException
        val pageFindResult: Optional<Page> = pageRepository.findById(id)
        val page: Page = pageFindResult.get() // TODO NoSuchElementException
        val position: Int = page.path.indexOf("/${id}/") + 2
        val count: Int = pageRepository.move(parentPage.path, page.path, position)
        return "${count}件更新しました parentPage.path=${parentPage.path} page.path=${page.path} position=${position}"
    }

    @Transactional
    fun delete(id: Int): Int {
        return pageRepository.delete(id)
    }

    fun saveImage(pageId: Int, name: String, path: String): Image {
        // TODO ページ存在チェック
        val image = Image(
            pageId = pageId,
            name = name,
            path = path
        )
        return imageRepository.save(image)
    }

    fun findImages(pageId: Int): Iterable<Image> {
        return imageRepository.findByPageIdIs(pageId)
    }

}