package com.example.jpa.service

import com.example.jpa.repository.PageRepository
import com.example.jpa.repository.SequenceRepository
import com.example.jpa.entity.PageEntity
import com.example.jpa.entity.SequenceEntity
import org.springframework.stereotype.Service

@Service
class PageService(
    private val pageRepository: PageRepository,
    private val sequenceRepository: SequenceRepository
) {

    fun list(): Iterable<PageEntity> {
        return pageRepository.findAll()
    }

    fun createNew(): SequenceEntity {
        return sequenceRepository.save(SequenceEntity())
    }

}