package com.nyuad.gulflens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyuad.gulflens.data.CompanyCatalog
import com.nyuad.gulflens.data.EntryRouteCatalog
import com.nyuad.gulflens.data.StartupCatalog
import com.nyuad.gulflens.i18n.AppLanguage
import com.nyuad.gulflens.i18n.Strings
import com.nyuad.gulflens.i18n.TextKey
import com.nyuad.gulflens.i18n.companyDescription
import com.nyuad.gulflens.i18n.companyLocation
import com.nyuad.gulflens.i18n.companyName
import com.nyuad.gulflens.i18n.companyTags
import com.nyuad.gulflens.i18n.customerTypeLabel
import com.nyuad.gulflens.i18n.industryLabel
import com.nyuad.gulflens.i18n.moneyBandLabel
import com.nyuad.gulflens.i18n.productDescription
import com.nyuad.gulflens.i18n.productName
import com.nyuad.gulflens.i18n.productTypeLabel
import com.nyuad.gulflens.i18n.revenueBandLabel
import com.nyuad.gulflens.i18n.routeDescription
import com.nyuad.gulflens.i18n.routeTitle
import com.nyuad.gulflens.i18n.subindustryLabel
import com.nyuad.gulflens.i18n.teamBandLabel
import com.nyuad.gulflens.i18n.validationLabel
import com.nyuad.gulflens.i18n.visaBandLabel
import com.nyuad.gulflens.model.CompanyProfile
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.Product
import com.nyuad.gulflens.model.StartupProfileAnswers
import com.nyuad.gulflens.model.StartupScaleAnswers
import com.nyuad.gulflens.model.StartupScenario
import com.nyuad.gulflens.ui.components.GulfLensCard
import com.nyuad.gulflens.ui.components.GulfLensPageHeader
import com.nyuad.gulflens.ui.components.GulfLensPill
import com.nyuad.gulflens.ui.components.GulfLensScreen
import com.nyuad.gulflens.ui.theme.GulfLensSpacing

@Composable
fun CompanyProfileScreen(
    language: AppLanguage,
    onCompanySelected: (String) -> Unit,
    onBack: () -> Unit,
) {
    GulfLensScreen {
        GulfLensPageHeader(
            title = Strings.get(TextKey.COMPANY_TITLE, language),
            description = Strings.get(TextKey.COMPANY_LEAD, language),
        )

        ResponsiveGrid(items = CompanyCatalog.companies) { company ->
            GulfLensCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onCompanySelected(company.id) },
            ) {
                Text(companyIcon(company), style = MaterialTheme.typography.headlineMedium)
                Text(companyName(company, language), style = MaterialTheme.typography.titleLarge)
                Text(
                    companyDescription(company, language),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    "⌖  ${companyLocation(company, language)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                ResponsiveGrid(
                    items = companyTags(company, language),
                    twoColumnAt = 520.dp,
                ) { tag -> GulfLensPill(tag, modifier = Modifier.fillMaxWidth()) }
                Text(
                    "${Strings.get(TextKey.TRACK_COMPANY_CONTINUE, language)}  ${if (language.isRightToLeft) "←" else "→"}",
                    modifier = Modifier.padding(top = GulfLensSpacing.Small),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        ScreenActions(language = language, onBack = onBack)
    }
}

@Composable
fun StartupProfileScreen(
    language: AppLanguage,
    answers: StartupProfileAnswers,
    onAnswersChange: (StartupProfileAnswers) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
) {
    val subindustries = StartupCatalog.subindustries[answers.industryId].orEmpty()
    val progress = if (answers.isComplete) {
        Strings.get(TextKey.PROFILE_COMPLETE, language)
    } else {
        Strings.format(
            TextKey.PROFILE_PROGRESS_TEMPLATE,
            language,
            "answered" to answers.answeredCount,
        )
    }

    GulfLensScreen {
        GulfLensPageHeader(
            title = Strings.get(TextKey.PROFILE_TITLE, language),
            description = Strings.get(TextKey.PROFILE_LEAD, language),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                Strings.get(TextKey.PROFILE_META, language),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                Strings.get(TextKey.ALL_FIELDS_REQUIRED, language),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        OptionPicker(
            title = Strings.get(TextKey.FIELD_INDUSTRY, language),
            hint = Strings.get(TextKey.FIELD_INDUSTRY_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_INDUSTRY_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.industries,
            selectedId = answers.industryId,
            onSelected = { industryId ->
                onAnswersChange(answers.copy(industryId = industryId, subindustryId = ""))
            },
            optionLabel = { industryLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_SUBINDUSTRY, language),
            hint = Strings.get(TextKey.FIELD_SUBINDUSTRY_HINT, language),
            placeholder = Strings.get(
                if (answers.industryId.isBlank()) TextKey.FIELD_SUBINDUSTRY_FIRST_PLACEHOLDER
                else TextKey.FIELD_SUBINDUSTRY_PLACEHOLDER,
                language,
            ),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = subindustries,
            selectedId = answers.subindustryId,
            enabled = answers.industryId.isNotBlank(),
            onSelected = { onAnswersChange(answers.copy(subindustryId = it)) },
            optionLabel = { subindustryLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_PRODUCT_TYPE, language),
            hint = Strings.get(TextKey.FIELD_PRODUCT_TYPE_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_PRODUCT_TYPE_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.productTypes,
            selectedId = answers.productTypeId,
            onSelected = { onAnswersChange(answers.copy(productTypeId = it)) },
            optionLabel = { productTypeLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_CUSTOMER, language),
            hint = Strings.get(TextKey.FIELD_CUSTOMER_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_CUSTOMER_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.customerTypes,
            selectedId = answers.customerTypeId,
            onSelected = { onAnswersChange(answers.copy(customerTypeId = it)) },
            optionLabel = { customerTypeLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_VALIDATION, language),
            hint = Strings.get(TextKey.FIELD_VALIDATION_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_VALIDATION_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.validationStages,
            selectedId = answers.validationId,
            onSelected = { onAnswersChange(answers.copy(validationId = it)) },
            optionLabel = { validationLabel(it, language) },
        )

        ScreenActions(
            language = language,
            onBack = onBack,
            primaryText = Strings.get(TextKey.PROFILE_CONTINUE, language),
            onPrimary = onContinue,
            primaryEnabled = answers.isComplete,
            status = progress,
        )
    }
}

@Composable
fun CompanyRouteScreen(
    language: AppLanguage,
    company: CompanyProfile,
    selectedProductId: String,
    selectedMode: EntryMode,
    onProductSelected: (String) -> Unit,
    onModeSelected: (EntryMode) -> Unit,
    onRunAnalysis: () -> Unit,
    onBack: () -> Unit,
) {
    val selectedProduct = company.products.first { it.id == selectedProductId }
    val title = Strings.format(
        TextKey.PRODUCT_ROUTE_TITLE_TEMPLATE,
        language,
        "company" to companyName(company, language),
    )

    GulfLensScreen {
        GulfLensPageHeader(
            title = title,
            description = Strings.get(TextKey.PRODUCT_ROUTE_LEAD, language),
        )

        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val isWide = maxWidth >= 820.dp
            if (isWide) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large),
                    verticalAlignment = Alignment.Top,
                ) {
                    ProductPanel(
                        language,
                        company,
                        selectedProductId,
                        onProductSelected,
                        Modifier.weight(1f),
                    )
                    RoutePanel(
                        language,
                        selectedProduct,
                        selectedMode,
                        onModeSelected,
                        Modifier.weight(1f),
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Large)) {
                    ProductPanel(language, company, selectedProductId, onProductSelected)
                    RoutePanel(language, selectedProduct, selectedMode, onModeSelected)
                }
            }
        }

        ScreenActions(
            language = language,
            onBack = onBack,
            primaryText = Strings.get(TextKey.RUN_CITY_ANALYSIS, language),
            onPrimary = onRunAnalysis,
        )
    }
}

@Composable
private fun ProductPanel(
    language: AppLanguage,
    company: CompanyProfile,
    selectedProductId: String,
    onProductSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium)) {
        Text(
            "${Strings.get(TextKey.PRODUCTS_FROM, language)} ${companyName(company, language)}",
            style = MaterialTheme.typography.titleMedium,
        )
        company.products.forEachIndexed { index, product ->
            GulfLensCard(
                modifier = Modifier.fillMaxWidth(),
                selected = product.id == selectedProductId,
                onClick = { onProductSelected(product.id) },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium),
                    verticalAlignment = Alignment.Top,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Small),
                    ) {
                        Text(productName(product, language), style = MaterialTheme.typography.titleMedium)
                        Text(
                            productDescription(product, language),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    NumberBadge(index + 1)
                }
            }
        }
    }
}

@Composable
private fun RoutePanel(
    language: AppLanguage,
    product: Product,
    selectedMode: EntryMode,
    onModeSelected: (EntryMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium)) {
        Text(
            Strings.get(TextKey.ENTRY_ROUTE_FOR_PRODUCT, language),
            style = MaterialTheme.typography.titleMedium,
        )
        EntryRouteCatalog.routes.forEach { route ->
            val enabled = product.availabilityFor(route.mode).isSelectable
            GulfLensCard(
                modifier = Modifier.fillMaxWidth(),
                selected = enabled && selectedMode == route.mode,
                enabled = enabled,
                onClick = { onModeSelected(route.mode) },
            ) {
                Text(routeTitle(route.mode, language), style = MaterialTheme.typography.titleMedium)
                Text(
                    routeDescription(route.mode, language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Text(
            "ⓘ  ${Strings.get(TextKey.ROUTE_CHOOSE_NOTE, language)}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun StartupPlanningScreen(
    language: AppLanguage,
    scenario: StartupScenario,
    answers: StartupScaleAnswers,
    onAnswersChange: (StartupScaleAnswers) -> Unit,
    onAnalyze: () -> Unit,
    onBack: () -> Unit,
) {
    val progress = Strings.format(
        TextKey.SCALE_PROGRESS_TEMPLATE,
        language,
        "answered" to answers.answeredCount,
    )
    val summaryValues = if (language == AppLanguage.English) {
        listOf(
            scenario.operatingSummary.operatingModel,
            scenario.operatingSummary.inputModel,
            scenario.operatingSummary.dataModel,
            scenario.operatingSummary.evidenceStatus,
        )
    } else {
        listOf(
            Strings.get(TextKey.SCALE_FLEXIBLE_SETUP, language),
            Strings.get(TextKey.SCALE_INPUTS_FROM_OFFER, language),
            Strings.get(TextKey.SCALE_CUSTOMER_DATA_REVIEW, language),
            Strings.get(TextKey.SCALE_EVIDENCE_REVIEW, language),
        )
    }

    GulfLensScreen {
        GulfLensPageHeader(
            title = Strings.get(TextKey.SCALE_TITLE, language),
            description = Strings.get(TextKey.SCALE_LEAD, language),
        )
        GulfLensCard(modifier = Modifier.fillMaxWidth()) {
            Text(Strings.get(TextKey.SCALE_OPERATING_PLAN, language), style = MaterialTheme.typography.titleMedium)
            ResponsiveGrid(items = summaryValues, twoColumnAt = 600.dp) { value ->
                GulfLensPill(text = value, modifier = Modifier.fillMaxWidth())
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                Strings.get(TextKey.SCALE_META, language),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                Strings.get(TextKey.ALL_FIELDS_REQUIRED, language),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        OptionPicker(
            title = Strings.get(TextKey.FIELD_INVESTMENT, language),
            hint = Strings.get(TextKey.FIELD_INVESTMENT_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_INVESTMENT_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.investmentBands,
            selectedId = answers.investmentId,
            onSelected = { onAnswersChange(answers.copy(investmentId = it)) },
            optionLabel = { moneyBandLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_REVENUE, language),
            hint = Strings.get(TextKey.FIELD_REVENUE_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_REVENUE_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.revenueBands,
            selectedId = answers.revenueId,
            onSelected = { onAnswersChange(answers.copy(revenueId = it)) },
            optionLabel = { revenueBandLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_TEAM, language),
            hint = Strings.get(TextKey.FIELD_TEAM_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_TEAM_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.teamSizeBands,
            selectedId = answers.teamSizeId,
            onSelected = { onAnswersChange(answers.copy(teamSizeId = it)) },
            optionLabel = { teamBandLabel(it, language) },
        )
        OptionPicker(
            title = Strings.get(TextKey.FIELD_VISAS, language),
            hint = Strings.get(TextKey.FIELD_VISAS_HINT, language),
            placeholder = Strings.get(TextKey.FIELD_VISAS_PLACEHOLDER, language),
            dismissText = Strings.get(TextKey.CLOSE, language),
            options = StartupCatalog.visaBands,
            selectedId = answers.visaCountId,
            onSelected = { onAnswersChange(answers.copy(visaCountId = it)) },
            optionLabel = { visaBandLabel(it, language) },
        )

        ScreenActions(
            language = language,
            onBack = onBack,
            primaryText = Strings.get(TextKey.ANALYSE_CITIES, language),
            onPrimary = onAnalyze,
            primaryEnabled = answers.isComplete,
            status = progress,
        )
    }
}

private fun companyIcon(company: CompanyProfile): String = when (company.id) {
    "cava" -> "◉"
    "warby-parker" -> "◌"
    "freshpet" -> "◇"
    else -> "▣"
}
