package com.nyuad.gulflens.domain

import com.nyuad.gulflens.data.BenchmarkCatalog
import com.nyuad.gulflens.model.ComparisonResult
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.MetricGroup
import com.nyuad.gulflens.model.MetricId
import com.nyuad.gulflens.model.WeightedCityScores
import com.nyuad.gulflens.model.WeightedMetricResult
import kotlin.math.floor

/** Pure, deterministic scoring logic shared by every platform. */
object ComparisonEngine {
    fun compare(
        entryMode: EntryMode,
        metricGroups: List<MetricGroup> = BenchmarkCatalog.metricGroups,
    ): ComparisonResult {
        val weights = requireNotNull(BenchmarkCatalog.metricWeights[entryMode]) {
            "No weights configured for $entryMode."
        }
        validate(metricGroups, weights)

        val breakdown = metricGroups.map { metric ->
            val weight = weights.getValue(metric.id)
            WeightedMetricResult(
                metric = metric,
                weightPercent = weight,
                contributions = WeightedCityScores(
                    dubai = metric.scores.dubai * weight / 100.0,
                    abuDhabi = metric.scores.abuDhabi * weight / 100.0,
                ),
            )
        }

        return ComparisonResult(
            entryMode = entryMode,
            scores = WeightedCityScores(
                dubai = roundToOneDecimal(breakdown.sumOf { it.contributions.dubai }),
                abuDhabi = roundToOneDecimal(breakdown.sumOf { it.contributions.abuDhabi }),
            ),
            breakdown = breakdown,
        )
    }

    private fun validate(
        metricGroups: List<MetricGroup>,
        weights: Map<MetricId, Int>,
    ) {
        require(
            metricGroups.size == MetricId.entries.size &&
                metricGroups.map { it.id }.toSet() == MetricId.entries.toSet(),
        ) {
            "The comparison requires exactly one group for every metric."
        }
        require(weights.keys == MetricId.entries.toSet()) {
            "The selected route must define a weight for every metric."
        }
        require(weights.values.sum() == 100) {
            "Metric weights must total 100."
        }
        metricGroups.forEach { metric ->
            require(metric.scores.dubai in 0..100 && metric.scores.abuDhabi in 0..100) {
                "Metric scores must be between 0 and 100."
            }
        }
    }

    // All scores are non-negative; this mirrors JavaScript Math.round used by
    // the reference prototype (including half-up behaviour).
    private fun roundToOneDecimal(value: Double): Double = floor(value * 10.0 + 0.5) / 10.0
}
