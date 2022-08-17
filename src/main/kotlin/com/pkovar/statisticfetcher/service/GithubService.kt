package com.pkovar.statisticfetcher.service

import com.google.gson.Gson
import com.pkovar.statisticfetcher.dto.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class GithubService(
    private val client: WebClient
) {
    companion object {
        val GITHUB_API_BASE_URL = "https://api.github.com"
    }

    fun getRepositoryList(): List<Repository> {
        val response = client
            .get()
            .uri("$GITHUB_API_BASE_URL/orgs/productboard/repos?per_page=100")
            .retrieve()
            .bodyToMono<String>()
            .block()
        return Gson().fromJson(response, Array<Repository>::class.java).toList()
    }

    fun getRepositoryLanguageData(repositoryName: String): Map<String, Double> = client
            .get()
            .uri("$GITHUB_API_BASE_URL/repos/productboard/$repositoryName/languages")
            .retrieve()
            .bodyToMono<Map<String, Double>>()
            .block().orEmpty()
}
