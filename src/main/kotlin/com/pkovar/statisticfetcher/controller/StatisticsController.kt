package com.pkovar.statisticfetcher.controller

import com.pkovar.statisticfetcher.service.IStatisticProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatisticsController(
    private val statisticProviderService: IStatisticProviderService
    )
{
    @GetMapping("/statistic")
    fun getStatistics(): Map<String, Double> =
        statisticProviderService.getLatestLanguageStats()
}
