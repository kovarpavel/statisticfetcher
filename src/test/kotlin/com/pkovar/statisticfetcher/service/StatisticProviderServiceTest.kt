package com.pkovar.statisticfetcher.service

import com.pkovar.statisticfetcher.entity.LanguageStatsEntity
import com.pkovar.statisticfetcher.repository.LanguageStatsRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.sql.Timestamp

@ExtendWith(SpringExtension::class)
@SpringBootTest
class StatisticProviderServiceTest {

    @Autowired
    private lateinit var languageStatsRepository: LanguageStatsRepository

    @BeforeEach
    fun init() {
        languageStatsRepository.save(
            LanguageStatsEntity(
                stats=mapOf(Pair("C", 0.4), Pair("Java", 0.6)),
                loadedAt = Timestamp(System.currentTimeMillis() - 100)
            )
        )
        languageStatsRepository.save(
            LanguageStatsEntity(
                stats=mapOf(Pair("C", 0.5), Pair("Java", 0.5)),
                loadedAt = Timestamp(System.currentTimeMillis())
            )
        )
    }

    @Test
    fun `getLatestLanguageStats should return latest statistics`() {
        val service = StatisticProviderServiceImpl(languageStatsRepository)
        assertThat(service.getLatestLanguageStats()).isEqualTo(mapOf(Pair("C", 0.5), Pair("Java", 0.5)))
    }
}