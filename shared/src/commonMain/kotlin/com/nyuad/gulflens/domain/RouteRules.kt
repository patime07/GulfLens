package com.nyuad.gulflens.domain

import com.nyuad.gulflens.data.StartupCatalog
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.Product
import com.nyuad.gulflens.model.RouteAvailability
import com.nyuad.gulflens.model.StartupOperatingSummary
import com.nyuad.gulflens.model.StartupProfileAnswers
import com.nyuad.gulflens.model.StartupScenario

object RouteRules {
    fun firstSelectableMode(product: Product): EntryMode? =
        EntryMode.entries.firstOrNull {
            product.availabilityFor(it) == RouteAvailability.AVAILABLE
        } ?: EntryMode.entries.firstOrNull {
            product.availabilityFor(it) == RouteAvailability.CONDITIONAL
        }

    /**
     * Derives startup route eligibility using the exact rule order from the
     * reference prototype. Later rules intentionally override earlier ones.
     */
    fun startupAvailability(answers: StartupProfileAnswers): Map<EntryMode, RouteAvailability> {
        require(answers.isComplete) { "All startup profile answers are required." }

        val status = baseStartupAvailability(answers.productTypeId).toMutableMap()
        val regulated = answers.subindustryId in setOf("medical-devices", "optical-vision")
        val regulatedDigital =
            answers.subindustryId in setOf("fintech-payments", "digital-health") &&
                answers.productTypeId in setOf("software", "marketplace")
        val industrialHardware =
            answers.industryId == "industrial-manufacturing" && answers.productTypeId == "hardware"
        val wholesaleGoods =
            answers.subindustryId == "wholesale-distribution" &&
                answers.productTypeId in setOf("physical", "hardware")

        if (regulated) {
            status[EntryMode.REMOTE] = RouteAvailability.UNAVAILABLE
            status[EntryMode.IMPORT] = RouteAvailability.CONDITIONAL
            status[EntryMode.LOCAL] = RouteAvailability.CONDITIONAL
        }
        if (regulatedDigital) {
            status[EntryMode.REMOTE] = RouteAvailability.CONDITIONAL
        }
        if (industrialHardware) {
            status[EntryMode.IMPORT] = RouteAvailability.AVAILABLE
            status[EntryMode.LOCAL] = RouteAvailability.CONDITIONAL
        }
        if (wholesaleGoods) {
            status[EntryMode.IMPORT] = RouteAvailability.AVAILABLE
            status[EntryMode.LOCAL] = RouteAvailability.AVAILABLE
        }

        return status.toMap()
    }

    private fun baseStartupAvailability(productTypeId: String): Map<EntryMode, RouteAvailability> =
        when (productTypeId) {
            "software", "service", "marketplace" -> availability(
                remote = RouteAvailability.AVAILABLE,
                importRoute = RouteAvailability.UNAVAILABLE,
                localRoute = RouteAvailability.CONDITIONAL,
            )

            "physical" -> availability(
                remote = RouteAvailability.UNAVAILABLE,
                importRoute = RouteAvailability.AVAILABLE,
                localRoute = RouteAvailability.CONDITIONAL,
            )

            "food", "hardware" -> availability(
                remote = RouteAvailability.UNAVAILABLE,
                importRoute = RouteAvailability.CONDITIONAL,
                localRoute = RouteAvailability.CONDITIONAL,
            )

            "local-service" -> availability(
                remote = RouteAvailability.UNAVAILABLE,
                importRoute = RouteAvailability.UNAVAILABLE,
                localRoute = RouteAvailability.AVAILABLE,
            )

            else -> error("Unknown startup product type: $productTypeId")
        }

    private fun availability(
        remote: RouteAvailability,
        importRoute: RouteAvailability,
        localRoute: RouteAvailability,
    ): Map<EntryMode, RouteAvailability> = mapOf(
        EntryMode.REMOTE to remote,
        EntryMode.IMPORT to importRoute,
        EntryMode.LOCAL to localRoute,
    )
}

object StartupScenarioFactory {
    private val productSuffixes = mapOf(
        "software" to "software platform",
        "service" to "managed service",
        "physical" to "consumer product",
        "food" to "food & beverage offer",
        "hardware" to "hardware product",
        "local-service" to "UAE operation",
        "marketplace" to "marketplace",
    )

    private val validationDescriptions = mapOf(
        "none" to "no UAE-specific validation yet",
        "research" to "UAE market research or inbound interest",
        "interviews" to "completed UAE customer interviews",
        "pilot" to "a signed LOI, pilot or distributor discussion",
        "paying" to "paying UAE customers or repeat sales",
    )

    private val operatingModels = mapOf(
        "software" to "Digital-first operations",
        "service" to "Remote or flexible office",
        "physical" to "Retail or distribution space",
        "food" to "Commercial kitchen or retail",
        "hardware" to "Light industrial setup",
        "local-service" to "Customer-facing premises",
        "marketplace" to "Digital platform operations",
    )

    private val inputModels = mapOf(
        "software" to "No physical inventory",
        "service" to "People-led delivery",
        "physical" to "Finished-goods inventory",
        "food" to "Raw materials and cold chain",
        "hardware" to "Components and equipment",
        "local-service" to "Local operating inputs",
        "marketplace" to "No physical inventory",
    )

    private val evidenceModels = mapOf(
        "none" to "Early evidence — review required",
        "research" to "Market evidence — review required",
        "interviews" to "Customer evidence developing",
        "pilot" to "Pilot or partner evidence",
        "paying" to "Paying-customer evidence",
    )

    fun create(answers: StartupProfileAnswers): StartupScenario {
        require(answers.isComplete) { "All startup profile answers are required." }

        val industry = requireNotNull(StartupCatalog.industry(answers.industryId)) {
            "Unknown startup industry: ${answers.industryId}"
        }
        val subindustry = requireNotNull(
            StartupCatalog.subindustry(answers.industryId, answers.subindustryId),
        ) { "Unknown startup sub-industry: ${answers.subindustryId}" }
        val productType = requireNotNull(StartupCatalog.productType(answers.productTypeId)) {
            "Unknown startup product type: ${answers.productTypeId}"
        }
        val customer = requireNotNull(StartupCatalog.customerType(answers.customerTypeId)) {
            "Unknown startup customer type: ${answers.customerTypeId}"
        }
        val validation = requireNotNull(validationDescriptions[answers.validationId]) {
            "Unknown startup validation stage: ${answers.validationId}"
        }
        val suffix = requireNotNull(productSuffixes[answers.productTypeId])

        val profileName = "${subindustry.label} venture"
        val productName = "${subindustry.label} $suffix"
        val product = Product(
            id = "startup-${answers.subindustryId}-${answers.productTypeId}",
            name = productName,
            description = "${productType.label} for ${customer.label.lowercase()} in the ${industry.label.lowercase()} sector, supported by $validation.",
            routeAvailability = RouteRules.startupAvailability(answers),
        )

        return StartupScenario(
            profileName = profileName,
            product = product,
            operatingSummary = StartupOperatingSummary(
                operatingModel = operatingModels.getValue(answers.productTypeId),
                inputModel = inputModels.getValue(answers.productTypeId),
                dataModel = dataModelFor(answers),
                evidenceStatus = evidenceModels.getValue(answers.validationId),
            ),
        )
    }

    private fun dataModelFor(answers: StartupProfileAnswers): String {
        val handlesSensitiveData = answers.subindustryId in setOf(
            "fintech-payments",
            "digital-health",
            "medical-devices",
            "optical-vision",
        )
        return when {
            handlesSensitiveData -> "Sensitive customer data"
            answers.customerTypeId == "b2b" -> "Business contact data"
            else -> "Customer profile data"
        }
    }
}
