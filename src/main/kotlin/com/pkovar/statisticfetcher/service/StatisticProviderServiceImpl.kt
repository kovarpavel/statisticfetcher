package com.pkovar.statisticfetcher.service

import com.pkovar.statisticfetcher.repository.LanguageStatsRepository
import org.springframework.stereotype.Service

@Service
class StatisticProviderServiceImpl (
    private val languageStatsRepository: LanguageStatsRepository
    ) : IStatisticProviderService {

    override fun getLatestLanguageStats(): Map<String, Double> {
        val record = languageStatsRepository.findFirstByOrderByLoadedAtDesc()
        return record?.stats ?: mapOf<String,Double>()
    }
}