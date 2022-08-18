package com.pkovar.statisticfetcher.service

import com.pkovar.statisticfetcher.dto.Repository
import com.pkovar.statisticfetcher.repository.LanguageStatsRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class StatisticCollectorServiceTest {

    @MockBean
    private lateinit var githubService: GithubServiceImpl

    @Autowired
    private lateinit var languageStatsRepository: LanguageStatsRepository

    @Test
    fun `getPercentageLanguageData should return calculated percentage language stats`() {
        Mockito.`when`(githubService.getRepositoryList()).thenReturn(listOf(Repository("repo1"), Repository("repo2")))
        Mockito.`when`(githubService.getRepositoryLanguageData("repo1")).thenReturn(mapOf(Pair("C", 200.0), Pair("Java", 350.0)))
        Mockito.`when`(githubService.getRepositoryLanguageData("repo2")).thenReturn(mapOf(Pair("Java", 400.0), Pair("Python", 350.0)))

        val service = StatisticCollectorServiceImpl(githubService, languageStatsRepository)

        assertThat(service.getPercentageLanguageData()).isEqualTo(
            mapOf(
                Pair("Java", 0.577),
                Pair("C", 0.154),
                Pair("Python", 0.27)
            )
        )
    }
}