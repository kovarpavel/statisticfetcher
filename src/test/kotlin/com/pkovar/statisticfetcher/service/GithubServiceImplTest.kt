package com.pkovar.statisticfetcher.service

import com.pkovar.statisticfetcher.dto.Repository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@SpringBootTest
class GithubServiceImplTest {

    @MockBean
    private lateinit var exchangeFunction: ExchangeFunction

    @Test
    fun`Github service should return list of repository names`() {
        val response: ClientResponse = ClientResponse.create(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body("[{\"name\": \"repo1\"},{\"name\": \"repo2\"}]").build()

        val webClient = WebClient.builder().exchangeFunction(exchangeFunction).build()

        Mockito.`when`(exchangeFunction.exchange(any(ClientRequest::class.java)))
            .thenReturn(Mono.just(response))

        val service = GithubServiceImpl(webClient)

        assertThat(service.getRepositoryList())
            .isEqualTo(listOf(Repository("repo1"), Repository("repo2")))
    }

    @Test
    fun `Github service should return language stats for given repository name`() {
        val response: ClientResponse = ClientResponse.create(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body("{\"C\": 0.4, \"Java\": 0.5, \"Python\": 0.1}").build()

        val webClient = WebClient.builder().exchangeFunction(exchangeFunction).build()

        Mockito.`when`(exchangeFunction.exchange(any(ClientRequest::class.java)))
            .thenReturn(Mono.just(response))

        val service = GithubServiceImpl(webClient)

        assertThat(service.getRepositoryLanguageData("repo1"))
            .isEqualTo(mapOf(Pair("C", 0.4), Pair("Java", 0.5), Pair("Python", 0.1)))
    }

}