package com.pkovar.statisticfetcher.service

import com.pkovar.statisticfetcher.entity.LanguageStatsEntity
import com.pkovar.statisticfetcher.repository.LanguageStatsRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.math.RoundingMode
import java.sql.Timestamp

@Service
class StatisticService(
    private val githubService: GithubService,
    private val languageStatsRepository: LanguageStatsRepository
) {
    private fun gatherData(): Map<String,Double> {
        val githubData = mutableMapOf<String,Double>()

        githubService.getRepositoryList().forEach { repository ->
            val repositoryLangStats = githubService.getRepositoryLanguageData(repository.name)
            repositoryLangStats.forEach { (k, v) ->
                if (githubData[k] == null) {
                    githubData[k] = v
                } else {
                    val amount:Double? = githubData[k]?.plus(v);
                    githubData[k] = amount!!;
                }
            }
        }
        return githubData
    }

    private fun getPercentageLanguageData():Map<String, Double> {
        val githubData = gatherData()
        val totalSize = githubData.values.sum();
        val languagePercentData = mutableMapOf<String, Double>()
        githubData.forEach { (k, v) ->
            languagePercentData[k] = ((1 / totalSize ) * v)
                .toBigDecimal()
                .setScale(3, RoundingMode.CEILING)
                .toDouble()
        }
        return languagePercentData
    }

    @Scheduled(cron = "0 * * * * *")
    private fun saveLanguageData() {
        languageStatsRepository.save(
            LanguageStatsEntity(
                stats = getPercentageLanguageData(),
                loadedAt = Timestamp(System.currentTimeMillis())
            )
        )
    }

    fun getLatestLanguageStats(): Map<String, Double> {
        val record = languageStatsRepository.findFirstByOrderByLoadedAtDesc()
        return record?.stats ?: mapOf<String,Double>()
    }

}