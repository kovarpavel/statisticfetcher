package com.pkovar.statisticfetcher.controller

import com.pkovar.statisticfetcher.service.StatisticService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
class StatisticsController(
    private val statisticService: StatisticService
) {

    @GetMapping("/statistic")
    fun getStatistics(): Map<String, Double> =
        statisticService.getLatestLanguageStats()

}

