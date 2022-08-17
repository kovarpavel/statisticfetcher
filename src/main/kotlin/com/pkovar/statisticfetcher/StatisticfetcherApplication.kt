package com.pkovar.statisticfetcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class StatisticfetcherApplication

fun main(args: Array<String>) {
	runApplication<StatisticfetcherApplication>(*args)
}
