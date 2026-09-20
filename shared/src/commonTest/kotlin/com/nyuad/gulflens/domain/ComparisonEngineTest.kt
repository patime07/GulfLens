package com.nyuad.gulflens.domain

import com.nyuad.gulflens.data.BenchmarkCatalog
import com.nyuad.gulflens.model.EntryMode
import kotlin.test.Test
import kotlin.test.assertEquals

class ComparisonEngineTest {
    @Test
    fun everyWeightSetTotalsOneHundred() {
        BenchmarkCatalog.metricWeights.values.forEach { weights ->
            assertEquals(100, weights.values.sum())
        }
    }

    @Test
    fun remoteScoresMatchPrototype() {
        val result = ComparisonEngine.compare(EntryMode.REMOTE)

        assertEquals(81.6, result.scores.dubai)
        assertEquals(77.8, result.scores.abuDhabi)
    }

    @Test
    fun importScoresMatchPrototype() {
        val result = ComparisonEngine.compare(EntryMode.IMPORT)

        assertEquals(80.3, result.scores.dubai)
        assertEquals(78.2, result.scores.abuDhabi)
    }

    @Test
    fun localScoresMatchPrototype() {
        val result = ComparisonEngine.compare(EntryMode.LOCAL)

        assertEquals(79.6, result.scores.dubai)
        assertEquals(78.9, result.scores.abuDhabi)
    }
}
