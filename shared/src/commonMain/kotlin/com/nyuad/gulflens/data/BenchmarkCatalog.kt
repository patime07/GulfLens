package com.nyuad.gulflens.data

import com.nyuad.gulflens.model.City
import com.nyuad.gulflens.model.CityMarketSentiment
import com.nyuad.gulflens.model.CityScores
import com.nyuad.gulflens.model.Confidence
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.MarketSignal
import com.nyuad.gulflens.model.MetricGroup
import com.nyuad.gulflens.model.MetricId
import com.nyuad.gulflens.model.MetricValue
import com.nyuad.gulflens.model.Submetric

object BenchmarkCatalog {
    val metricGroups: List<MetricGroup> = listOf(
        MetricGroup(
            id = MetricId.REGULATORY,
            title = "Regulatory & Setup Fit",
            description = "Lawful access, licensing and operating permissions",
            scores = CityScores(dubai = 77, abuDhabi = 78),
            confidence = Confidence.HIGH,
            insight = "The route is viable in both cities; additional approvals create the most friction.",
            submetrics = listOf(
                labeledSubmetric("Activity eligibility", "Hard gate", "Pass", "Pass"),
                scoredSubmetric("Licensing path", "Official rule", 82, 84),
                scoredSubmetric("Legal form & ownership", "Official rule", 88, 88),
                scoredSubmetric("Business location & premises", "Official rule", 74, 80),
                scoredSubmetric("Additional authority approvals", "Official rule", 62, 68),
                scoredSubmetric("Product / service obligations", "Official rule", 72, 70),
                scoredSubmetric("Tax & customs registration", "Federal rule", 80, 80),
                scoredSubmetric("Employment & visa compliance", "Federal rule", 78, 78),
            ),
        ),
        MetricGroup(
            id = MetricId.COST,
            title = "Cost Attractiveness",
            description = "Setup and recurring costs relative to the scenario",
            scores = CityScores(dubai = 68, abuDhabi = 82),
            confidence = Confidence.MEDIUM,
            insight = "Largest difference: premises and utilities favour Abu Dhabi in this scenario.",
            submetrics = listOf(
                scoredSubmetric("Licence & setup cost", "Official fee", 65, 82),
                scoredSubmetric("Workspace or facility", "Benchmark", 52, 88),
                scoredSubmetric("Payroll & benefits", "Benchmark", 68, 79),
                scoredSubmetric("Visas & insurance", "Official fee", 72, 80),
                scoredSubmetric("Utilities & connectivity", "Benchmark", 64, 92),
                scoredSubmetric("Delivery & fulfilment", "Benchmark", 78, 76),
                scoredSubmetric("Renewal & compliance overhead", "Model input", 77, 77),
            ),
        ),
        MetricGroup(
            id = MetricId.OPPORTUNITY,
            title = "Market Opportunity",
            description = "Demand, customer access and competitive headroom",
            scores = CityScores(dubai = 84, abuDhabi = 72),
            confidence = Confidence.MEDIUM,
            insight = "Largest difference: addressable demand and customer access favour Dubai.",
            submetrics = listOf(
                scoredSubmetric("Sector demand", "Market data", 94, 76),
                scoredSubmetric("Growth momentum", "Market data", 88, 74),
                scoredSubmetric("Willingness to pay", "Benchmark", 84, 72),
                scoredSubmetric("Customer access", "Benchmark", 88, 70),
                scoredSubmetric("Channel fit", "Benchmark", 78, 68),
                scoredSubmetric("Competitive headroom", "Market data", 72, 72),
            ),
        ),
        MetricGroup(
            id = MetricId.OPERATIONS,
            title = "Operating Environment",
            description = "Infrastructure, logistics and facility suitability",
            scores = CityScores(dubai = 88, abuDhabi = 84),
            confidence = Confidence.MEDIUM,
            insight = "Dubai leads on regional connectivity; Abu Dhabi leads on facility suitability.",
            submetrics = listOf(
                scoredSubmetric("Regional connectivity", "Benchmark", 94, 82),
                scoredSubmetric("Logistics network", "Benchmark", 92, 78),
                scoredSubmetric("Infrastructure reliability", "Benchmark", 88, 88),
                scoredSubmetric("Facility suitability", "Benchmark", 74, 88),
                scoredSubmetric("Digital & payment infrastructure", "Benchmark", 92, 86),
            ),
        ),
        MetricGroup(
            id = MetricId.TALENT,
            title = "Talent & Ecosystem",
            description = "People, partners and specialist support capacity",
            scores = CityScores(dubai = 86, abuDhabi = 80),
            confidence = Confidence.MEDIUM,
            insight = "Dubai’s deeper commercial-services network offsets higher employment cost.",
            submetrics = listOf(
                scoredSubmetric("Relevant talent availability", "Labour data", 86, 78),
                scoredSubmetric("Sector-specific expertise", "Benchmark", 88, 76),
                scoredSubmetric("Partner ecosystem", "Benchmark", 90, 84),
                scoredSubmetric("Relocation & retention fit", "Benchmark", 74, 78),
                scoredSubmetric("Professional services depth", "Benchmark", 92, 84),
            ),
        ),
    )

    /** Route-specific weights from the six-screen prototype. Every set totals 100. */
    val metricWeights: Map<EntryMode, Map<MetricId, Int>> = mapOf(
        EntryMode.REMOTE to mapOf(
            MetricId.REGULATORY to 15,
            MetricId.COST to 15,
            MetricId.OPPORTUNITY to 35,
            MetricId.OPERATIONS to 15,
            MetricId.TALENT to 20,
        ),
        EntryMode.IMPORT to mapOf(
            MetricId.REGULATORY to 20,
            MetricId.COST to 20,
            MetricId.OPPORTUNITY to 30,
            MetricId.OPERATIONS to 15,
            MetricId.TALENT to 15,
        ),
        EntryMode.LOCAL to mapOf(
            MetricId.REGULATORY to 20,
            MetricId.COST to 25,
            MetricId.OPPORTUNITY to 25,
            MetricId.OPERATIONS to 20,
            MetricId.TALENT to 10,
        ),
    )

    val marketSentiments: List<CityMarketSentiment> = listOf(
        CityMarketSentiment(
            city = City.DUBAI,
            positioning = "Energetic and opportunity-led",
            rating = "Strongly positive",
            summary = "Directional signals suggest Dubai may feel visible, fast-moving and opportunity-rich.",
            signals = listOf(
                MarketSignal(
                    title = "Customer sentiment",
                    description = "More upbeat; buyers may be easier to reach, with high expectations for speed and differentiation.",
                ),
                MarketSignal(
                    title = "Talent and partner sentiment",
                    description = "More confident; specialist hiring and partner discovery may feel easier in a highly visible ecosystem.",
                ),
                MarketSignal(
                    title = "Competitive intensity",
                    description = "High pressure; stronger demand is perceived alongside a crowded, fast-moving market.",
                ),
            ),
        ),
        CityMarketSentiment(
            city = City.ABU_DHABI,
            positioning = "Confident and stability-led",
            rating = "Positive",
            summary = "Directional signals suggest Abu Dhabi may feel measured, relationship-led and supportive of deliberate scale.",
            signals = listOf(
                MarketSignal(
                    title = "Customer sentiment",
                    description = "Positive but deliberate; trust, references and relationship-building may matter more before purchase.",
                ),
                MarketSignal(
                    title = "Talent and partner sentiment",
                    description = "Constructive; networks may feel smaller and more relationship-led, with local credibility carrying more weight.",
                ),
                MarketSignal(
                    title = "Competitive intensity",
                    description = "Moderate pressure; the market may feel less saturated, although the addressable customer pool can feel narrower.",
                ),
            ),
        ),
    )

    fun sentimentFor(city: City): CityMarketSentiment =
        marketSentiments.first { it.city == city }

    private fun scoredSubmetric(
        name: String,
        basis: String,
        dubai: Int,
        abuDhabi: Int,
    ) = Submetric(
        name = name,
        basis = basis,
        dubai = MetricValue.Score(dubai),
        abuDhabi = MetricValue.Score(abuDhabi),
    )

    private fun labeledSubmetric(
        name: String,
        basis: String,
        dubai: String,
        abuDhabi: String,
    ) = Submetric(
        name = name,
        basis = basis,
        dubai = MetricValue.Label(dubai),
        abuDhabi = MetricValue.Label(abuDhabi),
    )
}
