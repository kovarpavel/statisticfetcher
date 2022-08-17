package com.pkovar.statisticfetcher.repository

import com.pkovar.statisticfetcher.entity.LanguageStatsEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageStatsRepository : CrudRepository<LanguageStatsEntity, Long> {

    fun findFirstByOrderByLoadedAtDesc(): LanguageStatsEntity?

}