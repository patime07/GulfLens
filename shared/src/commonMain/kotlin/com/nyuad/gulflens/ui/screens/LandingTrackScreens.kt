package com.nyuad.gulflens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nyuad.gulflens.i18n.AppLanguage
import com.nyuad.gulflens.i18n.Strings
import com.nyuad.gulflens.i18n.TextKey
import com.nyuad.gulflens.model.AssessmentTrack
import com.nyuad.gulflens.ui.components.GulfLensCard
import com.nyuad.gulflens.ui.components.GulfLensPrimaryButton
import com.nyuad.gulflens.ui.components.GulfLensScreen
import com.nyuad.gulflens.ui.theme.GulfLensColors
import com.nyuad.gulflens.ui.theme.GulfLensSpacing

@Composable
fun LandingScreen(
    language: AppLanguage,
    onStart: () -> Unit,
) {
    val inputKeys = listOf(
        TextKey.LANDING_INPUT_COMPANY_OR_STARTUP,
        TextKey.LANDING_INPUT_BUSINESS_PROFILE,
        TextKey.LANDING_INPUT_PRODUCT_OR_SERVICE,
        TextKey.LANDING_INPUT_OPERATING_SECTOR,
        TextKey.LANDING_INPUT_ENTRY_MODE,
    )
    val outputKeys = listOf(
        TextKey.LANDING_OUTPUT_ELIGIBILITY,
        TextKey.LANDING_OUTPUT_LICENSING,
        TextKey.LANDING_OUTPUT_COSTS,
        TextKey.LANDING_OUTPUT_MARGIN,
        TextKey.LANDING_OUTPUT_NEXT_STEPS,
    )

    GulfLensScreen(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            if (maxWidth >= 900.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BubbleGroup(
                        title = Strings.get(TextKey.LANDING_INPUTS_LABEL, language),
                        items = inputKeys.map { Strings.get(it, language) },
                        modifier = Modifier.weight(0.8f),
                        alignEnd = true,
                        bubbleColor = GulfLensColors.Blue,
                    )
                    LandingHero(
                        language = language,
                        onStart = onStart,
                        modifier = Modifier.weight(1.4f),
                    )
                    BubbleGroup(
                        title = Strings.get(TextKey.LANDING_OUTPUTS_LABEL, language),
                        items = outputKeys.map { Strings.get(it, language) },
                        modifier = Modifier.weight(0.8f),
                        alignEnd = false,
                        bubbleColor = GulfLensColors.GreenTeal,
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.XLarge),
                ) {
                    LandingHero(language, onStart, Modifier.fillMaxWidth())
                    ResponsiveGrid(
                        items = listOf(
                            Triple(
                                Strings.get(TextKey.LANDING_INPUTS_LABEL, language),
                                inputKeys,
                                GulfLensColors.Blue,
                            ),
                            Triple(
                                Strings.get(TextKey.LANDING_OUTPUTS_LABEL, language),
                                outputKeys,
                                GulfLensColors.GreenTeal,
                            ),
                        ),
                        twoColumnAt = 640.dp,
                    ) { (title, keys, color) ->
                        BubbleGroup(
                            title = title,
                            items = keys.map { Strings.get(it, language) },
                            alignEnd = false,
                            bubbleColor = color,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium),
        ) {
            Text(
                Strings.get(TextKey.LANDING_EXPECTATIONS_A11Y, language),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { heading() },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
            val expectations = listOf(
                Triple("◎", TextKey.LANDING_EXPECTATION_FIT_TITLE, TextKey.LANDING_EXPECTATION_FIT_BODY),
                Triple("↗", TextKey.LANDING_EXPECTATION_ROUTE_TITLE, TextKey.LANDING_EXPECTATION_ROUTE_BODY),
                Triple("∑", TextKey.LANDING_EXPECTATION_ECONOMICS_TITLE, TextKey.LANDING_EXPECTATION_ECONOMICS_BODY),
                Triple("✓", TextKey.LANDING_EXPECTATION_BRIEF_TITLE, TextKey.LANDING_EXPECTATION_BRIEF_BODY),
            )
            ResponsiveGrid(items = expectations) { (icon, titleKey, bodyKey) ->
                GulfLensCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        icon,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                    Text(Strings.get(titleKey, language), style = MaterialTheme.typography.titleMedium)
                    Text(
                        Strings.get(bodyKey, language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
private fun LandingHero(
    language: AppLanguage,
    onStart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large),
    ) {
        Text(
            text = Strings.get(TextKey.LANDING_TITLE_LEAD, language) + "\n" +
                Strings.get(TextKey.LANDING_TITLE_EMPHASIS, language),
            modifier = Modifier.semantics { heading() },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            Strings.get(TextKey.LANDING_LEAD, language),
            modifier = Modifier.widthIn(max = 680.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        GulfLensPrimaryButton(
            text = Strings.get(TextKey.LANDING_START, language),
            onClick = onStart,
            modifier = Modifier.widthIn(min = 280.dp, max = 420.dp),
        )
    }
}

@Composable
private fun BubbleGroup(
    title: String,
    items: List<String>,
    alignEnd: Boolean,
    bubbleColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (alignEnd) Alignment.End else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(13.dp),
    ) {
        Text(
            title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        items.forEachIndexed { index, item ->
            ConversationBubble(
                text = item,
                color = bubbleColor,
                modifier = Modifier
                    .fillMaxWidth(if ((index + if (alignEnd) 0 else 1) % 2 == 0) 0.92f else 0.78f)
                    .padding(
                        start = if (!alignEnd && index % 2 == 0) 18.dp else 0.dp,
                        end = if (alignEnd && index % 2 == 0) 18.dp else 0.dp,
                    ),
            )
        }
    }
}

@Composable
fun TrackScreen(
    language: AppLanguage,
    onSelectTrack: (AssessmentTrack) -> Unit,
    onBack: () -> Unit,
) {
    GulfLensScreen {
        Column(verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium)) {
            Text(
                Strings.get(TextKey.TRACK_TITLE, language),
                modifier = Modifier.semantics { heading() },
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                Strings.get(TextKey.TRACK_LEAD, language),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        ResponsiveGrid(
            items = listOf(AssessmentTrack.COMPANY, AssessmentTrack.STARTUP),
            twoColumnAt = 700.dp,
        ) { track ->
            val isCompany = track == AssessmentTrack.COMPANY
            val title = if (isCompany) TextKey.TRACK_COMPANY_TITLE else TextKey.TRACK_STARTUP_TITLE
            val body = if (isCompany) TextKey.TRACK_COMPANY_BODY else TextKey.TRACK_STARTUP_BODY
            val benefits = if (isCompany) {
                listOf(
                    TextKey.TRACK_COMPANY_BENEFIT_OPERATING,
                    TextKey.TRACK_COMPANY_BENEFIT_CURATED,
                    TextKey.TRACK_COMPANY_BENEFIT_SPEED,
                )
            } else {
                listOf(
                    TextKey.TRACK_STARTUP_BENEFIT_EARLY,
                    TextKey.TRACK_STARTUP_BENEFIT_GUIDED,
                    TextKey.TRACK_STARTUP_BENEFIT_FEASIBILITY,
                )
            }
            val action = if (isCompany) TextKey.TRACK_COMPANY_CONTINUE else TextKey.TRACK_STARTUP_CONTINUE

            GulfLensCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSelectTrack(track) },
                contentPadding = PaddingValues(GulfLensSpacing.Large),
            ) {
                Text(
                    if (isCompany) "▦" else "✦",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(Strings.get(title, language), style = MaterialTheme.typography.titleLarge)
                Text(
                    Strings.get(body, language),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                benefits.forEach { key ->
                    Text(
                        "• ${Strings.get(key, language)}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Text(
                    "${Strings.get(action, language)}  ${if (language.isRightToLeft) "←" else "→"}",
                    modifier = Modifier.padding(top = GulfLensSpacing.Medium),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        ScreenActions(language = language, onBack = onBack)
    }
}
