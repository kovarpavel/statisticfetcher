package com.pkovar.statisticfetcher.service

interface IStatisticCollectorService {
    fun getPercentageLanguageData():Map<String, Double>
}