package com.nyuad.gulflens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nyuad.gulflens.data.CompanyCatalog
import com.nyuad.gulflens.data.StartupCatalog
import com.nyuad.gulflens.domain.RouteRules
import com.nyuad.gulflens.domain.StartupScenarioFactory
import com.nyuad.gulflens.i18n.AppLanguage
import com.nyuad.gulflens.i18n.Strings
import com.nyuad.gulflens.i18n.TextKey
import com.nyuad.gulflens.i18n.companyName
import com.nyuad.gulflens.i18n.productName
import com.nyuad.gulflens.i18n.productTypeLabel
import com.nyuad.gulflens.i18n.subindustryLabel
import com.nyuad.gulflens.i18n.moneyBandLabel
import com.nyuad.gulflens.model.AssessmentTrack
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.StartupProfileAnswers
import com.nyuad.gulflens.model.StartupScaleAnswers
import com.nyuad.gulflens.presentation.AssessmentScreen
import com.nyuad.gulflens.presentation.AssessmentState
import com.nyuad.gulflens.ui.screens.AnalysisScreen
import com.nyuad.gulflens.ui.screens.CompanyProfileScreen
import com.nyuad.gulflens.ui.screens.CompanyRouteScreen
import com.nyuad.gulflens.ui.screens.LandingScreen
import com.nyuad.gulflens.ui.screens.LanguageMenu
import com.nyuad.gulflens.ui.screens.ResultsScreen
import com.nyuad.gulflens.ui.screens.StartupPlanningScreen
import com.nyuad.gulflens.ui.screens.StartupProfileScreen
import com.nyuad.gulflens.ui.screens.TrackScreen
import com.nyuad.gulflens.ui.theme.GulfLensTheme

/** Shared Compose entry point used by Android today and the Web target next. */
@Composable
fun App() {
    var state by rememberSaveable(stateSaver = assessmentStateSaver) {
        mutableStateOf(AssessmentState())
    }
    var languageTag by rememberSaveable { mutableStateOf(AppLanguage.English.tag) }
    var darkTheme by rememberSaveable { mutableStateOf(false) }
    val language = AppLanguage.fromTag(languageTag)
    val layoutDirection = if (language.isRightToLeft) LayoutDirection.Rtl else LayoutDirection.Ltr

    GulfLensBackHandler(enabled = state.canNavigateBack) {
        state = state.navigateBack()
    }

    GulfLensTheme(darkTheme = darkTheme) {
        CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                Box(Modifier.fillMaxSize()) {
                    UtilityControls(
                        language = language,
                        darkTheme = darkTheme,
                        onLanguageChanged = { languageTag = it.tag },
                        onThemeChanged = { darkTheme = !darkTheme },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .zIndex(2f),
                    )

                    when (state.screen) {
                        AssessmentScreen.LANDING -> LandingScreen(
                            language = language,
                            onStart = { state = state.copy(screen = AssessmentScreen.TRACK) },
                        )

                        AssessmentScreen.TRACK -> TrackScreen(
                            language = language,
                            onSelectTrack = { track -> state = state.selectTrack(track) },
                            onBack = { state = state.navigateBack() },
                        )

                        AssessmentScreen.PROFILE -> when (state.track) {
                            AssessmentTrack.COMPANY -> CompanyProfileScreen(
                                language = language,
                                onCompanySelected = { companyId ->
                                    state = state.selectCompany(companyId).copy(
                                        screen = AssessmentScreen.SCENARIO,
                                    )
                                },
                                onBack = { state = state.navigateBack() },
                            )

                            AssessmentTrack.STARTUP -> StartupProfileScreen(
                                language = language,
                                answers = state.startupProfile,
                                onAnswersChange = { answers ->
                                    state = state.copy(startupProfile = answers)
                                },
                                onContinue = {
                                    val scenario = StartupScenarioFactory.create(state.startupProfile)
                                    state = state.copy(
                                        screen = AssessmentScreen.SCENARIO,
                                        entryMode = requireNotNull(
                                            RouteRules.firstSelectableMode(scenario.product),
                                        ),
                                    )
                                },
                                onBack = { state = state.navigateBack() },
                            )
                        }

                        AssessmentScreen.SCENARIO -> when (state.track) {
                            AssessmentTrack.COMPANY -> {
                                val company = requireNotNull(CompanyCatalog.findCompany(state.companyId))
                                CompanyRouteScreen(
                                    language = language,
                                    company = company,
                                    selectedProductId = state.productId,
                                    selectedMode = state.entryMode,
                                    onProductSelected = { productId ->
                                        state = state.selectProduct(productId)
                                    },
                                    onModeSelected = { mode -> state = state.selectEntryMode(mode) },
                                    onRunAnalysis = {
                                        state = state.copy(screen = AssessmentScreen.ANALYSIS)
                                    },
                                    onBack = { state = state.navigateBack() },
                                )
                            }

                            AssessmentTrack.STARTUP -> StartupPlanningScreen(
                                language = language,
                                scenario = StartupScenarioFactory.create(state.startupProfile),
                                answers = state.startupScale,
                                onAnswersChange = { answers ->
                                    state = state.copy(startupScale = answers)
                                },
                                onAnalyze = {
                                    state = state.copy(screen = AssessmentScreen.ANALYSIS)
                                },
                                onBack = { state = state.navigateBack() },
                            )
                        }

                        AssessmentScreen.ANALYSIS -> AnalysisScreen(
                            language = language,
                            analysisKey = stableScenarioKey(state),
                            profileName = localizedProfileName(state, language),
                            productName = localizedProductName(state, language),
                            entryMode = state.entryMode,
                            additionalContext = localizedAnalysisExtras(state, language),
                            onBack = { state = state.navigateBack() },
                            onViewResults = {
                                state = state.copy(screen = AssessmentScreen.RESULTS)
                            },
                        )

                        AssessmentScreen.RESULTS -> ResultsScreen(
                            language = language,
                            profileName = localizedProfileName(state, language),
                            productName = localizedProductName(state, language),
                            entryMode = state.entryMode,
                            additionalContext = localizedAnalysisExtras(state, language),
                            onEditScenario = {
                                state = state.copy(screen = AssessmentScreen.SCENARIO)
                            },
                        )
                    }

                }
            }
        }
    }
}

private val assessmentStateSaver = listSaver<AssessmentState, String>(
    save = { state ->
        listOf(
            state.screen.name,
            state.track.name,
            state.companyId,
            state.productId,
            state.entryMode.name,
            state.startupProfile.industryId,
            state.startupProfile.subindustryId,
            state.startupProfile.productTypeId,
            state.startupProfile.customerTypeId,
            state.startupProfile.validationId,
            state.startupScale.investmentId,
            state.startupScale.revenueId,
            state.startupScale.teamSizeId,
            state.startupScale.visaCountId,
        )
    },
    restore = { saved ->
        runCatching {
            AssessmentState(
                screen = AssessmentScreen.valueOf(saved[0]),
                track = AssessmentTrack.valueOf(saved[1]),
                companyId = saved[2],
                productId = saved[3],
                entryMode = EntryMode.valueOf(saved[4]),
                startupProfile = StartupProfileAnswers(
                    industryId = saved[5],
                    subindustryId = saved[6],
                    productTypeId = saved[7],
                    customerTypeId = saved[8],
                    validationId = saved[9],
                ),
                startupScale = StartupScaleAnswers(
                    investmentId = saved[10],
                    revenueId = saved[11],
                    teamSizeId = saved[12],
                    visaCountId = saved[13],
                ),
            )
        }.getOrElse { AssessmentState() }
    },
)

@Composable
private fun UtilityControls(
    language: AppLanguage,
    darkTheme: Boolean,
    onLanguageChanged: (AppLanguage) -> Unit,
    onThemeChanged: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val themeAction = Strings.get(
        if (darkTheme) TextKey.LIGHT_MODE else TextKey.DARK_MODE,
        language,
    )
    Surface(
        modifier = modifier
            .safeDrawingPadding()
            .padding(12.dp),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LanguageMenu(
                current = language,
                onSelect = onLanguageChanged,
                accessibilityLabel = Strings.get(TextKey.LANGUAGE, language),
            )
            TextButton(
                onClick = onThemeChanged,
                modifier = Modifier.semantics { contentDescription = themeAction },
            ) {
                Text(if (darkTheme) "☀" else "☾", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

private fun localizedProfileName(state: AssessmentState, language: AppLanguage): String =
    when (state.track) {
        AssessmentTrack.COMPANY -> companyName(
            requireNotNull(CompanyCatalog.findCompany(state.companyId)),
            language,
        )
        AssessmentTrack.STARTUP -> {
            val subindustry = requireNotNull(
                StartupCatalog.subindustry(
                    state.startupProfile.industryId,
                    state.startupProfile.subindustryId,
                ),
            )
            Strings.format(
                TextKey.STARTUP_PROFILE_NAME_TEMPLATE,
                language,
                "subindustry" to subindustryLabel(subindustry, language),
            )
        }
    }

private fun localizedProductName(state: AssessmentState, language: AppLanguage): String =
    when (state.track) {
        AssessmentTrack.COMPANY -> productName(state.activeProduct(), language)
        AssessmentTrack.STARTUP -> {
            val subindustry = requireNotNull(
                StartupCatalog.subindustry(
                    state.startupProfile.industryId,
                    state.startupProfile.subindustryId,
                ),
            )
            val productType = requireNotNull(
                StartupCatalog.productType(state.startupProfile.productTypeId),
            )
            Strings.format(
                TextKey.STARTUP_PRODUCT_NAME_TEMPLATE,
                language,
                "subindustry" to subindustryLabel(subindustry, language),
                "productType" to productTypeLabel(productType, language),
            )
        }
    }

private fun stableScenarioKey(state: AssessmentState): String = listOf(
    state.track.name,
    state.companyId,
    state.productId,
    state.entryMode.name,
    state.startupProfile.industryId,
    state.startupProfile.subindustryId,
    state.startupProfile.productTypeId,
    state.startupProfile.customerTypeId,
    state.startupProfile.validationId,
    state.startupScale.investmentId,
    state.startupScale.revenueId,
    state.startupScale.teamSizeId,
    state.startupScale.visaCountId,
).joinToString("|")

private fun localizedAnalysisExtras(
    state: AssessmentState,
    language: AppLanguage,
): List<String> {
    if (state.track != AssessmentTrack.STARTUP) return emptyList()
    val investment = StartupCatalog.investmentBands.firstOrNull {
        it.id == state.startupScale.investmentId
    } ?: return emptyList()
    return listOf(moneyBandLabel(investment, language))
}
