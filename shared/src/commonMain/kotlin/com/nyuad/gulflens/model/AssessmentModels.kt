package com.nyuad.gulflens.model

/** The two assessment paths exposed by GulfLens. */
enum class AssessmentTrack {
    COMPANY,
    STARTUP,
}

/**
 * The three market-entry routes used throughout the prototype.
 *
 * The declaration order is intentional: it matches the order in which routes
 * are displayed and the order used when choosing a default selectable route.
 */
enum class EntryMode {
    REMOTE,
    IMPORT,
    LOCAL,
}

enum class RouteAvailability {
    AVAILABLE,
    CONDITIONAL,
    UNAVAILABLE;

    val isSelectable: Boolean
        get() = this != UNAVAILABLE
}

data class EntryRoute(
    val mode: EntryMode,
    val title: String,
    val description: String,
)

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val routeAvailability: Map<EntryMode, RouteAvailability>,
) {
    fun availabilityFor(mode: EntryMode): RouteAvailability =
        routeAvailability[mode] ?: RouteAvailability.UNAVAILABLE
}

data class CompanyProfile(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val tags: List<String>,
    val products: List<Product>,
)

data class CatalogOption(
    val id: String,
    val label: String,
)

/** The five structured answers collected on the startup profile screen. */
data class StartupProfileAnswers(
    val industryId: String = "",
    val subindustryId: String = "",
    val productTypeId: String = "",
    val customerTypeId: String = "",
    val validationId: String = "",
) {
    val answeredCount: Int
        get() = listOf(
            industryId,
            subindustryId,
            productTypeId,
            customerTypeId,
            validationId,
        ).count(String::isNotBlank)

    val isComplete: Boolean
        get() = answeredCount == 5
}

/** The four ranges collected on the startup planning screen. */
data class StartupScaleAnswers(
    val investmentId: String = "",
    val revenueId: String = "",
    val teamSizeId: String = "",
    val visaCountId: String = "",
) {
    val answeredCount: Int
        get() = listOf(
            investmentId,
            revenueId,
            teamSizeId,
            visaCountId,
        ).count(String::isNotBlank)

    val isComplete: Boolean
        get() = answeredCount == 4
}

data class StartupOperatingSummary(
    val operatingModel: String,
    val inputModel: String,
    val dataModel: String,
    val evidenceStatus: String,
)

data class StartupScenario(
    val profileName: String,
    val product: Product,
    val operatingSummary: StartupOperatingSummary,
)

enum class City {
    DUBAI,
    ABU_DHABI,
}

enum class MetricId {
    REGULATORY,
    COST,
    OPPORTUNITY,
    OPERATIONS,
    TALENT,
}

enum class Confidence {
    HIGH,
    MEDIUM,
}

data class CityScores(
    val dubai: Int,
    val abuDhabi: Int,
) {
    fun forCity(city: City): Int = when (city) {
        City.DUBAI -> dubai
        City.ABU_DHABI -> abuDhabi
    }
}

sealed interface MetricValue {
    data class Score(val value: Int) : MetricValue

    data class Label(val value: String) : MetricValue
}

data class Submetric(
    val name: String,
    val basis: String,
    val dubai: MetricValue,
    val abuDhabi: MetricValue,
)

data class MetricGroup(
    val id: MetricId,
    val title: String,
    val description: String,
    val scores: CityScores,
    val confidence: Confidence,
    val insight: String,
    val submetrics: List<Submetric>,
)

data class WeightedCityScores(
    val dubai: Double,
    val abuDhabi: Double,
) {
    fun forCity(city: City): Double = when (city) {
        City.DUBAI -> dubai
        City.ABU_DHABI -> abuDhabi
    }
}

data class WeightedMetricResult(
    val metric: MetricGroup,
    val weightPercent: Int,
    val contributions: WeightedCityScores,
)

data class ComparisonResult(
    val entryMode: EntryMode,
    val scores: WeightedCityScores,
    val breakdown: List<WeightedMetricResult>,
) {
    val gap: Double
        get() = kotlin.math.floor(
            kotlin.math.abs(scores.dubai - scores.abuDhabi) * 10.0 + 0.5,
        ) / 10.0

    val leadingCity: City?
        get() = when {
            scores.dubai > scores.abuDhabi -> City.DUBAI
            scores.abuDhabi > scores.dubai -> City.ABU_DHABI
            else -> null
        }
}

data class MarketSignal(
    val title: String,
    val description: String,
)

data class CityMarketSentiment(
    val city: City,
    val positioning: String,
    val rating: String,
    val summary: String,
    val signals: List<MarketSignal>,
)
