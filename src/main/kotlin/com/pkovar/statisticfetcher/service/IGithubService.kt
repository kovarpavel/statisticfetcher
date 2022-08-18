package com.pkovar.statisticfetcher.service

import com.pkovar.statisticfetcher.dto.Repository

interface IGithubService {
    fun getRepositoryList(): List<Repository>
    fun getRepositoryLanguageData(repositoryName: String): Map<String, Double>
}