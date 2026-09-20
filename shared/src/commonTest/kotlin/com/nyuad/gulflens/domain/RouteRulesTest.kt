package com.nyuad.gulflens.domain

import com.nyuad.gulflens.data.CompanyCatalog
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.RouteAvailability
import com.nyuad.gulflens.model.StartupProfileAnswers
import kotlin.test.Test
import kotlin.test.assertEquals

class RouteRulesTest {
    @Test
    fun availableRouteIsPreferredOverConditionalRoute() {
        val product = CompanyCatalog.findProduct("toast", "restaurant-pos-software")!!

        assertEquals(EntryMode.REMOTE, RouteRules.firstSelectableMode(product))
    }

    @Test
    fun conditionalRouteIsUsedWhenNoAvailableRouteExists() {
        val product = CompanyCatalog.findProduct("freshpet", "refrigerated-dog-food-rolls")!!

        assertEquals(EntryMode.IMPORT, RouteRules.firstSelectableMode(product))
    }

    @Test
    fun regulatedDigitalStartupMakesRemoteRouteConditional() {
        val answers = StartupProfileAnswers(
            industryId = "technology",
            subindustryId = "fintech-payments",
            productTypeId = "software",
            customerTypeId = "b2b",
            validationId = "none",
        )

        assertEquals(
            RouteAvailability.CONDITIONAL,
            RouteRules.startupAvailability(answers).getValue(EntryMode.REMOTE),
        )
    }

    @Test
    fun wholesaleGoodsAllowImportAndLocalRoutes() {
        val answers = StartupProfileAnswers(
            industryId = "retail-ecommerce",
            subindustryId = "wholesale-distribution",
            productTypeId = "physical",
            customerTypeId = "channel",
            validationId = "pilot",
        )

        val result = RouteRules.startupAvailability(answers)
        assertEquals(RouteAvailability.AVAILABLE, result.getValue(EntryMode.IMPORT))
        assertEquals(RouteAvailability.AVAILABLE, result.getValue(EntryMode.LOCAL))
    }
}
