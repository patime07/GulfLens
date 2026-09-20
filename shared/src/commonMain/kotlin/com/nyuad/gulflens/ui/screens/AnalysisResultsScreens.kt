package com.nyuad.gulflens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nyuad.gulflens.data.BenchmarkCatalog
import com.nyuad.gulflens.domain.ComparisonEngine
import com.nyuad.gulflens.i18n.AppLanguage
import com.nyuad.gulflens.i18n.Strings
import com.nyuad.gulflens.i18n.TextKey
import com.nyuad.gulflens.i18n.metricDescription
import com.nyuad.gulflens.i18n.metricInsight
import com.nyuad.gulflens.i18n.metricTitle
import com.nyuad.gulflens.i18n.routeTitle
import com.nyuad.gulflens.i18n.submetricBasis
import com.nyuad.gulflens.i18n.submetricName
import com.nyuad.gulflens.i18n.metricValueText
import com.nyuad.gulflens.model.City
import com.nyuad.gulflens.model.ComparisonResult
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.MetricGroup
import com.nyuad.gulflens.ui.components.GulfLensCard
import com.nyuad.gulflens.ui.components.GulfLensPageHeader
import com.nyuad.gulflens.ui.components.GulfLensPill
import com.nyuad.gulflens.ui.components.GulfLensScreen
import com.nyuad.gulflens.ui.components.GulfLensSecondaryButton
import com.nyuad.gulflens.ui.theme.GulfLensColors
import com.nyuad.gulflens.ui.theme.GulfLensSpacing
import kotlinx.coroutines.delay

@Composable
fun AnalysisScreen(
    language: AppLanguage,
    analysisKey: String,
    profileName: String,
    productName: String,
    entryMode: EntryMode,
    additionalContext: List<String> = emptyList(),
    onBack: () -> Unit,
    onViewResults: () -> Unit,
) {
    val metrics = BenchmarkCatalog.metricGroups
    var scoredGroups by remember(analysisKey, entryMode) { mutableStateOf(0) }
    var skipped by remember(analysisKey, entryMode) { mutableStateOf(false) }
    val ready = skipped || scoredGroups == metrics.size

    LaunchedEffect(analysisKey, entryMode) {
        scoredGroups = 0
        skipped = false
        metrics.indices.forEach { index ->
            delay(620)
            scoredGroups = index + 1
            delay(140)
        }
    }

    GulfLensScreen {
        GulfLensPageHeader(
            title = Strings.get(
                if (ready) TextKey.ANALYSIS_TITLE_READY else TextKey.ANALYSIS_TITLE_BUILDING,
                language,
            ),
            description = Strings.get(
                if (ready) TextKey.ANALYSIS_LEAD_READY else TextKey.ANALYSIS_LEAD_BUILDING,
                language,
            ),
        )

        ScenarioContext(
            items = listOf(profileName, productName, routeTitle(entryMode, language)) + additionalContext,
        )

        GulfLensCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    Strings.format(
                        TextKey.ANALYSIS_PROGRESS_TEMPLATE,
                        language,
                        "scored" to if (ready) metrics.size else scoredGroups,
                        "total" to metrics.size,
                    ),
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    Strings.get(TextKey.ANALYSIS_HIGHER_STRONGER, language),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            LinearProgressIndicator(
                progress = { (if (ready) 1f else scoredGroups.toFloat() / metrics.size) },
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.secondaryContainer,
            )
            ResponsiveGrid(
                items = listOf(
                    Strings.get(TextKey.DUBAI, language),
                    Strings.get(TextKey.ABU_DHABI, language),
                ),
                twoColumnAt = 560.dp,
            ) { city ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(city, style = MaterialTheme.typography.titleMedium)
                    Text(
                        Strings.get(
                            if (ready) TextKey.ANALYSIS_ELIGIBLE_CONDITIONAL
                            else TextKey.ANALYSIS_WAITING_GATE,
                            language,
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        metrics.forEachIndexed { index, metric ->
            MetricAnalysisCard(
                language = language,
                metric = metric,
                weight = BenchmarkCatalog.metricWeights.getValue(entryMode).getValue(metric.id),
                isScored = ready || index < scoredGroups,
                isActive = !ready && index == scoredGroups,
            )
        }

        Text(
            Strings.get(TextKey.ANALYSIS_NOTE, language),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        if (ready) {
            ScreenActions(
                language = language,
                onBack = onBack,
                primaryText = Strings.get(TextKey.ANALYSIS_VIEW_RESULTS, language),
                onPrimary = onViewResults,
            )
        } else {
            ScreenActions(
                language = language,
                onBack = onBack,
                primaryText = Strings.get(TextKey.ANALYSIS_SKIP, language),
                onPrimary = {
                    skipped = true
                    scoredGroups = metrics.size
                },
            )
        }
    }
}

@Composable
private fun MetricAnalysisCard(
    language: AppLanguage,
    metric: MetricGroup,
    weight: Int,
    isScored: Boolean,
    isActive: Boolean,
) {
    var expanded by remember(metric.id) { mutableStateOf(metric.id == com.nyuad.gulflens.model.MetricId.REGULATORY) }
    val status = when {
        isScored -> Strings.get(TextKey.ANALYSIS_SCORED, language)
        isActive -> Strings.get(TextKey.ANALYSIS_SCORING, language)
        else -> Strings.get(TextKey.ANALYSIS_QUEUED, language)
    }

    GulfLensCard(
        modifier = Modifier.fillMaxWidth(),
        selected = isActive,
        onClick = { expanded = !expanded },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                if (isScored) "✓" else if (isActive) "…" else "○",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(metricTitle(metric, language), style = MaterialTheme.typography.titleMedium)
                Text(
                    metricDescription(metric, language),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Text(if (expanded) "⌃" else "⌄")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large, Alignment.End),
        ) {
            ScoreValueLabel(
                label = Strings.get(TextKey.DUBAI, language),
                value = if (isScored) metric.scores.dubai.toString() else "—",
            )
            ScoreValueLabel(
                label = Strings.get(TextKey.ABU_DHABI, language),
                value = if (isScored) metric.scores.abuDhabi.toString() else "—",
            )
        }
        if (expanded) {
            Text(
                Strings.format(
                    TextKey.ANALYSIS_GROUP_WEIGHT_TEMPLATE,
                    language,
                    "weight" to weight,
                ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            if (isScored) {
                metric.submetrics.forEach { submetric ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Small),
                    ) {
                        Column {
                            Text(submetricName(submetric, language), style = MaterialTheme.typography.bodyMedium)
                            Text(
                                submetricBasis(submetric, language),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large, Alignment.End),
                        ) {
                            ScoreValueLabel(
                                label = Strings.get(TextKey.DUBAI, language),
                                value = metricValueText(submetric.dubai, language),
                                compact = true,
                            )
                            ScoreValueLabel(
                                label = Strings.get(TextKey.ABU_DHABI, language),
                                value = metricValueText(submetric.abuDhabi, language),
                                compact = true,
                            )
                        }
                    }
                }
                Text(
                    metricInsight(metric, language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                Text(
                    Strings.get(
                        if (isActive) TextKey.ANALYSIS_EVALUATING else TextKey.ANALYSIS_INPUTS_QUEUED,
                        language,
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Text(status, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun ResultsScreen(
    language: AppLanguage,
    profileName: String,
    productName: String,
    entryMode: EntryMode,
    additionalContext: List<String> = emptyList(),
    onEditScenario: () -> Unit,
) {
    val result = ComparisonEngine.compare(entryMode)

    GulfLensScreen {
        GulfLensPageHeader(
            title = Strings.get(TextKey.RESULTS_TITLE, language),
            description = Strings.get(TextKey.RESULTS_LEAD, language),
        )
        ScenarioContext(
            items = listOf(profileName, productName, routeTitle(entryMode, language)) + additionalContext,
        )
        Text(
            Strings.get(TextKey.RESULTS_ASSUMPTIONS_NOTE, language),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        ComparisonSectionHeader(
            title = Strings.get(TextKey.RESULTS_HARD_TITLE, language),
            body = Strings.get(TextKey.RESULTS_HARD_BODY, language),
            badge = Strings.get(TextKey.RESULTS_HARD_SCALE, language),
        )
        ResponsiveGrid(
            items = listOf(City.DUBAI, City.ABU_DHABI),
            twoColumnAt = 660.dp,
        ) { city ->
            HardScoreCard(language, city, result)
        }

        GulfLensCard(modifier = Modifier.fillMaxWidth()) {
            Text(hardGapSummary(language, result), style = MaterialTheme.typography.titleMedium)
            Text(
                Strings.get(TextKey.RESULTS_SIGNALS_EXCLUDED, language),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium)) {
            result.breakdown.forEach { row ->
                GulfLensCard(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(metricTitle(row.metric, language), style = MaterialTheme.typography.titleMedium)
                        Text(
                            Strings.format(
                                TextKey.RESULTS_WEIGHT_TEMPLATE,
                                language,
                                "weight" to row.weightPercent,
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large, Alignment.End),
                    ) {
                        ScoreLabel(Strings.get(TextKey.DUBAI, language), row.metric.scores.dubai)
                        ScoreLabel(Strings.get(TextKey.ABU_DHABI, language), row.metric.scores.abuDhabi)
                    }
                }
            }
        }

        ComparisonSectionHeader(
            title = Strings.get(TextKey.RESULTS_SOFT_TITLE, language),
            body = Strings.get(TextKey.RESULTS_SOFT_BODY, language),
            badge = Strings.get(TextKey.RESULTS_SOFT_SCALE, language),
        )
        ResponsiveGrid(
            items = listOf(City.DUBAI, City.ABU_DHABI),
            twoColumnAt = 660.dp,
        ) { city ->
            SentimentCard(language, city)
        }

        GulfLensCard(modifier = Modifier.fillMaxWidth()) {
            Text(Strings.get(TextKey.RESULTS_MARKETS_FEEL_TITLE, language), style = MaterialTheme.typography.titleMedium)
            Text(
                Strings.get(TextKey.RESULTS_MARKETS_FEEL_BODY, language),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                Strings.get(TextKey.RESULTS_SOFT_NOTE, language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        GulfLensCard(modifier = Modifier.fillMaxWidth(), selected = true) {
            Text(hardVerdict(language, result), style = MaterialTheme.typography.titleLarge)
            Text(
                Strings.get(TextKey.RESULTS_VERDICT_CONTEXT, language),
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        GulfLensSecondaryButton(
            text = Strings.get(TextKey.EDIT_SCENARIO, language),
            onClick = onEditScenario,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ScenarioContext(items: List<String>) {
    ResponsiveGrid(items = items.filter { it.isNotBlank() }, twoColumnAt = 540.dp) { item ->
        GulfLensPill(text = item, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun ComparisonSectionHeader(title: String, body: String, badge: String) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        if (maxWidth >= 620.dp) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium),
                verticalAlignment = Alignment.Top,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, modifier = Modifier.semantics { heading() }, style = MaterialTheme.typography.headlineMedium)
                    Text(body, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                GulfLensPill(badge)
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Small)) {
                Text(title, modifier = Modifier.semantics { heading() }, style = MaterialTheme.typography.headlineMedium)
                Text(body, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                GulfLensPill(badge)
            }
        }
    }
}

@Composable
private fun HardScoreCard(language: AppLanguage, city: City, result: ComparisonResult) {
    val isDubai = city == City.DUBAI
    val score = result.scores.forCity(city)
    val cityName = Strings.get(if (isDubai) TextKey.DUBAI else TextKey.ABU_DHABI, language)
    GulfLensCard(modifier = Modifier.fillMaxWidth(), selected = true) {
        Text(cityName, style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                formatScore(score),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Medium,
            )
            Text(
                "  ${Strings.get(TextKey.RESULTS_OUT_OF_100, language)}",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        LinearProgressIndicator(
            progress = { (score / 100.0).toFloat() },
            modifier = Modifier.fillMaxWidth(),
            color = if (isDubai) GulfLensColors.Teal else GulfLensColors.Blue,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Text(
            Strings.get(
                if (isDubai) TextKey.RESULTS_DUBAI_HARD_BODY else TextKey.RESULTS_ABU_HARD_BODY,
                language,
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ScoreLabel(label: String, score: Int) {
    ScoreValueLabel(label = label, value = score.toString())
}

@Composable
private fun ScoreValueLabel(
    label: String,
    value: String,
    compact: Boolean = false,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Text(
            value,
            textAlign = TextAlign.Center,
            style = if (compact) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun SentimentCard(language: AppLanguage, city: City) {
    val isDubai = city == City.DUBAI
    val cityName = Strings.get(if (isDubai) TextKey.DUBAI else TextKey.ABU_DHABI, language)
    val tagline = Strings.get(
        if (isDubai) TextKey.RESULTS_DUBAI_SENTIMENT_TAGLINE else TextKey.RESULTS_ABU_SENTIMENT_TAGLINE,
        language,
    )
    val rating = Strings.get(
        if (isDubai) TextKey.RESULTS_STRONGLY_POSITIVE else TextKey.RESULTS_POSITIVE,
        language,
    )
    val summary = Strings.get(
        if (isDubai) TextKey.RESULTS_DUBAI_SENTIMENT_SUMMARY else TextKey.RESULTS_ABU_SENTIMENT_SUMMARY,
        language,
    )
    val signals = listOf(
        TextKey.RESULTS_CUSTOMER_SENTIMENT to
            if (isDubai) TextKey.RESULTS_DUBAI_CUSTOMER_SENTIMENT else TextKey.RESULTS_ABU_CUSTOMER_SENTIMENT,
        TextKey.RESULTS_TALENT_PARTNER_SENTIMENT to
            if (isDubai) TextKey.RESULTS_DUBAI_TALENT_SENTIMENT else TextKey.RESULTS_ABU_TALENT_SENTIMENT,
        TextKey.RESULTS_COMPETITIVE_INTENSITY to
            if (isDubai) TextKey.RESULTS_DUBAI_COMPETITIVE_INTENSITY else TextKey.RESULTS_ABU_COMPETITIVE_INTENSITY,
    )

    GulfLensCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Small),
        ) {
            Column {
                Text(cityName, style = MaterialTheme.typography.titleLarge)
                Text(tagline, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            GulfLensPill(rating)
        }
        SentimentScale(language = language, activeIndex = if (isDubai) 3 else 2)
        Text(summary, style = MaterialTheme.typography.bodyLarge)
        signals.forEach { (title, body) ->
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(Strings.get(title, language), style = MaterialTheme.typography.titleSmall)
                Text(
                    Strings.get(body, language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun SentimentScale(
    language: AppLanguage,
    activeIndex: Int,
) {
    val labels = listOf(
        TextKey.RESULTS_CAUTIOUS,
        TextKey.RESULTS_MIXED,
        TextKey.RESULTS_POSITIVE,
        TextKey.RESULTS_STRONGLY_POSITIVE,
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        labels.forEachIndexed { index, key ->
            GulfLensPill(
                text = Strings.get(key, language),
                modifier = Modifier.weight(1f),
                containerColor = if (index == activeIndex) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
                contentColor = if (index == activeIndex) {
                    MaterialTheme.colorScheme.onSecondary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
        }
    }
}

private fun formatScore(value: Double): String {
    val tenths = kotlin.math.round(value * 10.0).toInt()
    return "${tenths / 10}.${kotlin.math.abs(tenths % 10)}"
}

private fun cityName(language: AppLanguage, city: City?): String = when (city) {
    City.DUBAI -> Strings.get(TextKey.DUBAI, language)
    City.ABU_DHABI -> Strings.get(TextKey.ABU_DHABI, language)
    null -> ""
}

private fun hardGapSummary(language: AppLanguage, result: ComparisonResult): String {
    val gap = formatScore(result.gap)
    val leader = cityName(language, result.leadingCity)
    return when {
        result.gap < 1.0 -> Strings.format(TextKey.RESULTS_NEAR_TIE_TEMPLATE, language, "gap" to gap)
        result.gap < 3.0 -> Strings.format(
            TextKey.RESULTS_NARROW_LEAD_TEMPLATE,
            language,
            "leader" to leader,
            "gap" to gap,
        )
        else -> Strings.format(
            TextKey.RESULTS_LEADS_TEMPLATE,
            language,
            "leader" to leader,
            "gap" to gap,
        )
    }
}

private fun hardVerdict(language: AppLanguage, result: ComparisonResult): String {
    val leader = cityName(language, result.leadingCity)
    return when {
        result.gap < 1.0 -> Strings.get(TextKey.RESULTS_HARD_TIED, language)
        result.gap < 3.0 -> Strings.format(
            TextKey.RESULTS_HARD_NARROW_TEMPLATE,
            language,
            "leader" to leader,
        )
        else -> Strings.format(
            TextKey.RESULTS_HARD_LEADS_TEMPLATE,
            language,
            "leader" to leader,
        )
    }
}
