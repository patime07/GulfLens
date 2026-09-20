package com.nyuad.gulflens.data

import com.nyuad.gulflens.model.CatalogOption

object StartupCatalog {
    val industries: List<CatalogOption> = listOf(
        CatalogOption("technology", "Technology"),
        CatalogOption("food-beverage", "Food & Beverage"),
        CatalogOption("consumer-goods", "Consumer Goods"),
        CatalogOption("healthcare-wellness", "Healthcare & Wellness"),
        CatalogOption("professional-services", "Professional Services"),
        CatalogOption("industrial-manufacturing", "Industrial & Manufacturing"),
        CatalogOption("retail-ecommerce", "Retail & E-commerce"),
    )

    val subindustries: Map<String, List<CatalogOption>> = mapOf(
        "technology" to listOf(
            CatalogOption("b2b-saas", "B2B SaaS"),
            CatalogOption("ai-data", "AI & data tools"),
            CatalogOption("fintech-payments", "Fintech & payments"),
            CatalogOption("marketplaces-platforms", "Marketplaces & platforms"),
        ),
        "food-beverage" to listOf(
            CatalogOption("restaurant-cafe", "Restaurant or café"),
            CatalogOption("packaged-food", "Packaged food"),
            CatalogOption("beverages", "Beverages"),
            CatalogOption("catering-cloud-kitchen", "Catering or cloud kitchen"),
        ),
        "consumer-goods" to listOf(
            CatalogOption("beauty-personal-care", "Beauty & personal care"),
            CatalogOption("apparel-accessories", "Apparel & accessories"),
            CatalogOption("home-lifestyle", "Home & lifestyle"),
            CatalogOption("pet-products", "Pet products"),
        ),
        "healthcare-wellness" to listOf(
            CatalogOption("digital-health", "Digital health"),
            CatalogOption("medical-devices", "Medical devices"),
            CatalogOption("fitness-wellness", "Fitness & wellness"),
            CatalogOption("optical-vision", "Optical & vision care"),
        ),
        "professional-services" to listOf(
            CatalogOption("consulting", "Consulting"),
            CatalogOption("marketing-creative", "Marketing & creative"),
            CatalogOption("education-training", "Education & training"),
            CatalogOption("recruitment-hr", "Recruitment & HR"),
        ),
        "industrial-manufacturing" to listOf(
            CatalogOption("light-manufacturing", "Light manufacturing"),
            CatalogOption("building-technology", "Building technology"),
            CatalogOption("mobility-components", "Mobility components"),
            CatalogOption("industrial-equipment", "Industrial equipment"),
        ),
        "retail-ecommerce" to listOf(
            CatalogOption("direct-to-consumer", "Direct-to-consumer brand"),
            CatalogOption("specialty-retail", "Specialty retail"),
            CatalogOption("omnichannel-retail", "Omnichannel retail"),
            CatalogOption("wholesale-distribution", "Wholesale & distribution"),
        ),
    )

    val productTypes: List<CatalogOption> = listOf(
        CatalogOption("software", "Software / digital platform"),
        CatalogOption("service", "Professional / managed service"),
        CatalogOption("physical", "Physical consumer product"),
        CatalogOption("food", "Food / beverage product"),
        CatalogOption("hardware", "Hardware / connected device"),
        CatalogOption("local-service", "Local venue / in-person service"),
        CatalogOption("marketplace", "Marketplace / transaction platform"),
    )

    val customerTypes: List<CatalogOption> = listOf(
        CatalogOption("b2b", "Businesses (B2B)"),
        CatalogOption("b2c", "Consumers (B2C)"),
        CatalogOption("b2g", "Government / public sector (B2G)"),
        CatalogOption("hospitality", "Hospitality operators"),
        CatalogOption("channel", "Retailers / distributors"),
        CatalogOption("mixed", "Mixed business and consumer"),
    )

    val validationStages: List<CatalogOption> = listOf(
        CatalogOption("none", "No UAE validation yet"),
        CatalogOption("research", "UAE market research or inbound interest"),
        CatalogOption("interviews", "UAE customer interviews completed"),
        CatalogOption("pilot", "Signed LOI, pilot or distributor interest"),
        CatalogOption("paying", "Paying UAE customers or repeat sales"),
    )

    val investmentBands: List<CatalogOption> = listOf(
        CatalogOption("under-250k", "Under AED 250,000"),
        CatalogOption("250k-499k", "AED 250,000–499,999"),
        CatalogOption("500k-999k", "AED 500,000–999,999"),
        CatalogOption("1m-2-49m", "AED 1–2.49 million"),
        CatalogOption("2-5m-4-99m", "AED 2.5–4.99 million"),
        CatalogOption("5m-plus", "AED 5 million+"),
        CatalogOption("unsure", "Not sure yet"),
    )

    val revenueBands: List<CatalogOption> = listOf(
        CatalogOption("pre-revenue", "Pre-revenue"),
        *investmentBands.toTypedArray(),
    )

    val teamSizeBands: List<CatalogOption> = listOf(
        CatalogOption("0", "0 — founders or remote team only"),
        CatalogOption("1-3", "1–3 people"),
        CatalogOption("4-9", "4–9 people"),
        CatalogOption("10-24", "10–24 people"),
        CatalogOption("25-49", "25–49 people"),
        CatalogOption("50-plus", "50+ people"),
        CatalogOption("unsure", "Not sure yet"),
    )

    val visaBands: List<CatalogOption> = listOf(
        CatalogOption("0", "0 visas"),
        CatalogOption("1-3", "1–3 visas"),
        CatalogOption("4-9", "4–9 visas"),
        CatalogOption("10-24", "10–24 visas"),
        CatalogOption("25-plus", "25+ visas"),
        CatalogOption("unsure", "Not sure yet"),
    )

    fun industry(id: String): CatalogOption? = industries.firstOrNull { it.id == id }

    fun subindustry(industryId: String, id: String): CatalogOption? =
        subindustries[industryId]?.firstOrNull { it.id == id }

    fun productType(id: String): CatalogOption? = productTypes.firstOrNull { it.id == id }

    fun customerType(id: String): CatalogOption? = customerTypes.firstOrNull { it.id == id }

    fun validationStage(id: String): CatalogOption? = validationStages.firstOrNull { it.id == id }
}
