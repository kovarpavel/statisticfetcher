package com.pkovar.statisticfetcher.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration {

    @Bean
    fun webClient(): WebClient {
        val strategies = ExchangeStrategies
            .builder()
            .codecs { config -> config.defaultCodecs().maxInMemorySize(16 * 1024 * 1024) }
            .build();
        return WebClient.builder()
            .exchangeStrategies(strategies)
            .build();
    }

}