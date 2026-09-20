package com.nyuad.gulflens.i18n

import com.nyuad.gulflens.model.CatalogOption
import com.nyuad.gulflens.model.CompanyProfile
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.MetricGroup
import com.nyuad.gulflens.model.MetricId
import com.nyuad.gulflens.model.MetricValue
import com.nyuad.gulflens.model.Product
import com.nyuad.gulflens.model.Submetric

fun text(language: AppLanguage, key: TextKey): String = Strings.get(key, language)

fun companyName(company: CompanyProfile, language: AppLanguage): String =
    Strings.get(companyKey(company.id, CompanyField.NAME), language)

fun companyDescription(company: CompanyProfile, language: AppLanguage): String =
    Strings.get(companyKey(company.id, CompanyField.DESCRIPTION), language)

fun companyLocation(company: CompanyProfile, language: AppLanguage): String =
    Strings.get(companyKey(company.id, CompanyField.LOCATION), language)

fun companyTags(company: CompanyProfile, language: AppLanguage): List<String> {
    val keys = when (company.id) {
        "cava" -> listOf(TextKey.COMPANY_CAVA_TAG_RESTAURANTS, TextKey.COMPANY_CAVA_TAG_FOOD)
        "warby-parker" -> listOf(TextKey.COMPANY_WARBY_PARKER_TAG_EYEWEAR, TextKey.COMPANY_WARBY_PARKER_TAG_RETAIL)
        "freshpet" -> listOf(TextKey.COMPANY_FRESHPET_TAG_PET_FOOD, TextKey.COMPANY_FRESHPET_TAG_COLD_CHAIN)
        "toast" -> listOf(TextKey.COMPANY_TOAST_TAG_TECH, TextKey.COMPANY_TOAST_TAG_HARDWARE)
        else -> return company.tags
    }
    return keys.map { Strings.get(it, language) }
}

fun productName(product: Product, language: AppLanguage): String =
    productKeys[product.id]?.first?.let { Strings.get(it, language) } ?: product.name

fun productDescription(product: Product, language: AppLanguage): String =
    productKeys[product.id]?.second?.let { Strings.get(it, language) } ?: product.description

fun routeTitle(mode: EntryMode, language: AppLanguage): String = Strings.get(
    when (mode) {
        EntryMode.REMOTE -> TextKey.ROUTE_REMOTE_TITLE
        EntryMode.IMPORT -> TextKey.ROUTE_IMPORT_TITLE
        EntryMode.LOCAL -> TextKey.ROUTE_LOCAL_TITLE
    },
    language,
)

fun routeDescription(mode: EntryMode, language: AppLanguage): String = Strings.get(
    when (mode) {
        EntryMode.REMOTE -> TextKey.ROUTE_REMOTE_BODY
        EntryMode.IMPORT -> TextKey.ROUTE_IMPORT_BODY
        EntryMode.LOCAL -> TextKey.ROUTE_LOCAL_BODY
    },
    language,
)

fun industryLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, industryKeys)

fun subindustryLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, subindustryKeys)

fun productTypeLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, productTypeKeys)

fun customerTypeLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, customerKeys)

fun validationLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, validationKeys)

fun moneyBandLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, moneyBandKeys)

fun revenueBandLabel(option: CatalogOption, language: AppLanguage): String =
    if (option.id == "pre-revenue") Strings.get(TextKey.REVENUE_PRE_REVENUE, language)
    else moneyBandLabel(option, language)

fun teamBandLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, teamBandKeys)

fun visaBandLabel(option: CatalogOption, language: AppLanguage): String =
    localizedOption(option, language, visaBandKeys)

fun metricTitle(metric: MetricGroup, language: AppLanguage): String = Strings.get(
    metricKeys.getValue(metric.id).title,
    language,
)

fun metricDescription(metric: MetricGroup, language: AppLanguage): String = Strings.get(
    metricKeys.getValue(metric.id).description,
    language,
)

fun metricInsight(metric: MetricGroup, language: AppLanguage): String = Strings.get(
    metricKeys.getValue(metric.id).insight,
    language,
)

fun submetricName(submetric: Submetric, language: AppLanguage): String =
    submetricNameKeys[submetric.name]?.let { Strings.get(it, language) } ?: submetric.name

fun submetricBasis(submetric: Submetric, language: AppLanguage): String =
    submetricBasisKeys[submetric.basis]?.let { Strings.get(it, language) } ?: submetric.basis

fun metricValueText(value: MetricValue, language: AppLanguage): String = when (value) {
    is MetricValue.Score -> value.value.toString()
    is MetricValue.Label -> if (value.value == "Pass") {
        Strings.get(TextKey.METRIC_PASS, language)
    } else {
        value.value
    }
}

private enum class CompanyField { NAME, DESCRIPTION, LOCATION }

private fun companyKey(id: String, field: CompanyField): TextKey = when (id) {
    "cava" -> when (field) {
        CompanyField.NAME -> TextKey.COMPANY_CAVA
        CompanyField.DESCRIPTION -> TextKey.COMPANY_CAVA_DESCRIPTION
        CompanyField.LOCATION -> TextKey.COMPANY_CAVA_LOCATION
    }
    "warby-parker" -> when (field) {
        CompanyField.NAME -> TextKey.COMPANY_WARBY_PARKER
        CompanyField.DESCRIPTION -> TextKey.COMPANY_WARBY_PARKER_DESCRIPTION
        CompanyField.LOCATION -> TextKey.COMPANY_WARBY_PARKER_LOCATION
    }
    "freshpet" -> when (field) {
        CompanyField.NAME -> TextKey.COMPANY_FRESHPET
        CompanyField.DESCRIPTION -> TextKey.COMPANY_FRESHPET_DESCRIPTION
        CompanyField.LOCATION -> TextKey.COMPANY_FRESHPET_LOCATION
    }
    else -> when (field) {
        CompanyField.NAME -> TextKey.COMPANY_TOAST
        CompanyField.DESCRIPTION -> TextKey.COMPANY_TOAST_DESCRIPTION
        CompanyField.LOCATION -> TextKey.COMPANY_TOAST_LOCATION
    }
}

private val productKeys = mapOf(
    "build-your-own-bowls-pitas" to (TextKey.PRODUCT_CAVA_BUILD_YOUR_OWN to TextKey.PRODUCT_CAVA_BUILD_YOUR_OWN_DESCRIPTION),
    "chef-curated-bowls-pitas" to (TextKey.PRODUCT_CAVA_CHEF_CURATED to TextKey.PRODUCT_CAVA_CHEF_CURATED_DESCRIPTION),
    "catering-group-bowl-bar" to (TextKey.PRODUCT_CAVA_CATERING to TextKey.PRODUCT_CAVA_CATERING_DESCRIPTION),
    "prescription-eyeglasses" to (TextKey.PRODUCT_WARBY_PRESCRIPTION to TextKey.PRODUCT_WARBY_PRESCRIPTION_DESCRIPTION),
    "non-prescription-sunglasses" to (TextKey.PRODUCT_WARBY_SUNGLASSES to TextKey.PRODUCT_WARBY_SUNGLASSES_DESCRIPTION),
    "contact-lenses" to (TextKey.PRODUCT_WARBY_CONTACTS to TextKey.PRODUCT_WARBY_CONTACTS_DESCRIPTION),
    "refrigerated-dog-food-rolls" to (TextKey.PRODUCT_FRESHPET_ROLLS to TextKey.PRODUCT_FRESHPET_ROLLS_DESCRIPTION),
    "refrigerated-bagged-dog-meals" to (TextKey.PRODUCT_FRESHPET_BAGGED_MEALS to TextKey.PRODUCT_FRESHPET_BAGGED_MEALS_DESCRIPTION),
    "refrigerated-cat-food" to (TextKey.PRODUCT_FRESHPET_CAT_FOOD to TextKey.PRODUCT_FRESHPET_CAT_FOOD_DESCRIPTION),
    "restaurant-pos-software" to (TextKey.PRODUCT_TOAST_POS to TextKey.PRODUCT_TOAST_POS_DESCRIPTION),
    "toast-go-3-handheld-platform" to (TextKey.PRODUCT_TOAST_GO to TextKey.PRODUCT_TOAST_GO_DESCRIPTION),
    "online-ordering-digital-storefront" to (TextKey.PRODUCT_TOAST_STOREFRONT to TextKey.PRODUCT_TOAST_STOREFRONT_DESCRIPTION),
)

private fun localizedOption(
    option: CatalogOption,
    language: AppLanguage,
    keys: Map<String, TextKey>,
): String = keys[option.id]?.let { Strings.get(it, language) } ?: option.label

private val industryKeys = mapOf(
    "technology" to TextKey.INDUSTRY_TECHNOLOGY,
    "food-beverage" to TextKey.INDUSTRY_FOOD_BEVERAGE,
    "consumer-goods" to TextKey.INDUSTRY_CONSUMER_GOODS,
    "healthcare-wellness" to TextKey.INDUSTRY_HEALTHCARE_WELLNESS,
    "professional-services" to TextKey.INDUSTRY_PROFESSIONAL_SERVICES,
    "industrial-manufacturing" to TextKey.INDUSTRY_INDUSTRIAL_MANUFACTURING,
    "retail-ecommerce" to TextKey.INDUSTRY_RETAIL_ECOMMERCE,
)

private val subindustryKeys = mapOf(
    "b2b-saas" to TextKey.SUBINDUSTRY_B2B_SAAS,
    "ai-data" to TextKey.SUBINDUSTRY_AI_DATA,
    "fintech-payments" to TextKey.SUBINDUSTRY_FINTECH_PAYMENTS,
    "marketplaces-platforms" to TextKey.SUBINDUSTRY_MARKETPLACES_PLATFORMS,
    "restaurant-cafe" to TextKey.SUBINDUSTRY_RESTAURANT_CAFE,
    "packaged-food" to TextKey.SUBINDUSTRY_PACKAGED_FOOD,
    "beverages" to TextKey.SUBINDUSTRY_BEVERAGES,
    "catering-cloud-kitchen" to TextKey.SUBINDUSTRY_CATERING_CLOUD_KITCHEN,
    "beauty-personal-care" to TextKey.SUBINDUSTRY_BEAUTY_PERSONAL_CARE,
    "apparel-accessories" to TextKey.SUBINDUSTRY_APPAREL_ACCESSORIES,
    "home-lifestyle" to TextKey.SUBINDUSTRY_HOME_LIFESTYLE,
    "pet-products" to TextKey.SUBINDUSTRY_PET_PRODUCTS,
    "digital-health" to TextKey.SUBINDUSTRY_DIGITAL_HEALTH,
    "medical-devices" to TextKey.SUBINDUSTRY_MEDICAL_DEVICES,
    "fitness-wellness" to TextKey.SUBINDUSTRY_FITNESS_WELLNESS,
    "optical-vision" to TextKey.SUBINDUSTRY_OPTICAL_VISION,
    "consulting" to TextKey.SUBINDUSTRY_CONSULTING,
    "marketing-creative" to TextKey.SUBINDUSTRY_MARKETING_CREATIVE,
    "education-training" to TextKey.SUBINDUSTRY_EDUCATION_TRAINING,
    "recruitment-hr" to TextKey.SUBINDUSTRY_RECRUITMENT_HR,
    "light-manufacturing" to TextKey.SUBINDUSTRY_LIGHT_MANUFACTURING,
    "building-technology" to TextKey.SUBINDUSTRY_BUILDING_TECHNOLOGY,
    "mobility-components" to TextKey.SUBINDUSTRY_MOBILITY_COMPONENTS,
    "industrial-equipment" to TextKey.SUBINDUSTRY_INDUSTRIAL_EQUIPMENT,
    "direct-to-consumer" to TextKey.SUBINDUSTRY_DIRECT_TO_CONSUMER,
    "specialty-retail" to TextKey.SUBINDUSTRY_SPECIALTY_RETAIL,
    "omnichannel-retail" to TextKey.SUBINDUSTRY_OMNICHANNEL_RETAIL,
    "wholesale-distribution" to TextKey.SUBINDUSTRY_WHOLESALE_DISTRIBUTION,
)

private val productTypeKeys = mapOf(
    "software" to TextKey.PRODUCT_TYPE_SOFTWARE,
    "service" to TextKey.PRODUCT_TYPE_SERVICE,
    "physical" to TextKey.PRODUCT_TYPE_PHYSICAL,
    "food" to TextKey.PRODUCT_TYPE_FOOD,
    "hardware" to TextKey.PRODUCT_TYPE_HARDWARE,
    "local-service" to TextKey.PRODUCT_TYPE_LOCAL_SERVICE,
    "marketplace" to TextKey.PRODUCT_TYPE_MARKETPLACE,
)

private val customerKeys = mapOf(
    "b2b" to TextKey.CUSTOMER_B2B,
    "b2c" to TextKey.CUSTOMER_B2C,
    "b2g" to TextKey.CUSTOMER_B2G,
    "hospitality" to TextKey.CUSTOMER_HOSPITALITY,
    "channel" to TextKey.CUSTOMER_CHANNEL,
    "mixed" to TextKey.CUSTOMER_MIXED,
)

private val validationKeys = mapOf(
    "none" to TextKey.VALIDATION_NONE,
    "research" to TextKey.VALIDATION_RESEARCH,
    "interviews" to TextKey.VALIDATION_INTERVIEWS,
    "pilot" to TextKey.VALIDATION_PILOT,
    "paying" to TextKey.VALIDATION_PAYING,
)

private val moneyBandKeys = mapOf(
    "under-250k" to TextKey.BAND_UNDER_250K,
    "250k-499k" to TextKey.BAND_250K_499K,
    "500k-999k" to TextKey.BAND_500K_999K,
    "1m-2-49m" to TextKey.BAND_1M_2_49M,
    "2-5m-4-99m" to TextKey.BAND_2_5M_4_99M,
    "5m-plus" to TextKey.BAND_5M_PLUS,
    "unsure" to TextKey.BAND_NOT_SURE,
)

private val teamBandKeys = mapOf(
    "0" to TextKey.TEAM_ZERO,
    "1-3" to TextKey.TEAM_ONE_THREE,
    "4-9" to TextKey.TEAM_FOUR_NINE,
    "10-24" to TextKey.TEAM_TEN_TWENTY_FOUR,
    "25-49" to TextKey.TEAM_TWENTY_FIVE_FORTY_NINE,
    "50-plus" to TextKey.TEAM_FIFTY_PLUS,
    "unsure" to TextKey.BAND_NOT_SURE,
)

private val visaBandKeys = mapOf(
    "0" to TextKey.VISAS_ZERO,
    "1-3" to TextKey.VISAS_ONE_THREE,
    "4-9" to TextKey.VISAS_FOUR_NINE,
    "10-24" to TextKey.VISAS_TEN_TWENTY_FOUR,
    "25-plus" to TextKey.VISAS_TWENTY_FIVE_PLUS,
    "unsure" to TextKey.BAND_NOT_SURE,
)

private data class MetricKeys(
    val title: TextKey,
    val description: TextKey,
    val insight: TextKey,
)

private val metricKeys = mapOf(
    MetricId.REGULATORY to MetricKeys(TextKey.GROUP_REGULATORY_TITLE, TextKey.GROUP_REGULATORY_SUBTITLE, TextKey.GROUP_REGULATORY_INSIGHT),
    MetricId.COST to MetricKeys(TextKey.GROUP_COST_TITLE, TextKey.GROUP_COST_SUBTITLE, TextKey.GROUP_COST_INSIGHT),
    MetricId.OPPORTUNITY to MetricKeys(TextKey.GROUP_OPPORTUNITY_TITLE, TextKey.GROUP_OPPORTUNITY_SUBTITLE, TextKey.GROUP_OPPORTUNITY_INSIGHT),
    MetricId.OPERATIONS to MetricKeys(TextKey.GROUP_OPERATIONS_TITLE, TextKey.GROUP_OPERATIONS_SUBTITLE, TextKey.GROUP_OPERATIONS_INSIGHT),
    MetricId.TALENT to MetricKeys(TextKey.GROUP_TALENT_TITLE, TextKey.GROUP_TALENT_SUBTITLE, TextKey.GROUP_TALENT_INSIGHT),
)

private val submetricNameKeys = mapOf(
    "Activity eligibility" to TextKey.METRIC_ACTIVITY_ELIGIBILITY,
    "Licensing path" to TextKey.METRIC_LICENSING_PATH,
    "Legal form & ownership" to TextKey.METRIC_LEGAL_FORM,
    "Business location & premises" to TextKey.METRIC_BUSINESS_LOCATION,
    "Additional authority approvals" to TextKey.METRIC_ADDITIONAL_APPROVALS,
    "Product / service obligations" to TextKey.METRIC_PRODUCT_OBLIGATIONS,
    "Tax & customs registration" to TextKey.METRIC_TAX_CUSTOMS,
    "Employment & visa compliance" to TextKey.METRIC_EMPLOYMENT_VISAS,
    "Licence & setup cost" to TextKey.METRIC_LICENCE_SETUP_COST,
    "Workspace or facility" to TextKey.METRIC_WORKSPACE_FACILITY,
    "Payroll & benefits" to TextKey.METRIC_PAYROLL_BENEFITS,
    "Visas & insurance" to TextKey.METRIC_VISAS_INSURANCE,
    "Utilities & connectivity" to TextKey.METRIC_UTILITIES_CONNECTIVITY,
    "Delivery & fulfilment" to TextKey.METRIC_DELIVERY_FULFILMENT,
    "Renewal & compliance overhead" to TextKey.METRIC_RENEWAL_COMPLIANCE,
    "Sector demand" to TextKey.METRIC_SECTOR_DEMAND,
    "Growth momentum" to TextKey.METRIC_GROWTH_MOMENTUM,
    "Willingness to pay" to TextKey.METRIC_WILLINGNESS_TO_PAY,
    "Customer access" to TextKey.METRIC_CUSTOMER_ACCESS,
    "Channel fit" to TextKey.METRIC_CHANNEL_FIT,
    "Competitive headroom" to TextKey.METRIC_COMPETITIVE_HEADROOM,
    "Regional connectivity" to TextKey.METRIC_REGIONAL_CONNECTIVITY,
    "Logistics network" to TextKey.METRIC_LOGISTICS_NETWORK,
    "Infrastructure reliability" to TextKey.METRIC_INFRASTRUCTURE_RELIABILITY,
    "Facility suitability" to TextKey.METRIC_FACILITY_SUITABILITY,
    "Digital & payment infrastructure" to TextKey.METRIC_DIGITAL_PAYMENTS,
    "Relevant talent availability" to TextKey.METRIC_TALENT_AVAILABILITY,
    "Sector-specific expertise" to TextKey.METRIC_SECTOR_EXPERTISE,
    "Partner ecosystem" to TextKey.METRIC_PARTNER_ECOSYSTEM,
    "Relocation & retention fit" to TextKey.METRIC_RELOCATION_RETENTION,
    "Professional services depth" to TextKey.METRIC_PROFESSIONAL_SERVICES,
)

private val submetricBasisKeys = mapOf(
    "Hard gate" to TextKey.METRIC_HARD_GATE,
    "Official rule" to TextKey.METRIC_OFFICIAL_RULE,
    "Federal rule" to TextKey.METRIC_FEDERAL_RULE,
    "Official fee" to TextKey.METRIC_OFFICIAL_FEE,
    "Benchmark" to TextKey.METRIC_BENCHMARK,
    "Model input" to TextKey.METRIC_MODEL_INPUT,
    "Market data" to TextKey.METRIC_MARKET_DATA,
    "Labour data" to TextKey.METRIC_LABOUR_DATA,
)
