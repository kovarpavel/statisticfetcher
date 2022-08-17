package com.pkovar.statisticfetcher.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
data class LanguageStatsEntity (

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    val id: Long = 0,

    @ElementCollection(fetch = FetchType.EAGER)
    val stats: Map<String, Double>,

    val loadedAt: Timestamp
)