package com.pkovar.statisticfetcher.service

interface IStatisticProviderService {
    fun getLatestLanguageStats(): Map<String, Double>
}