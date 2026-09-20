package com.nyuad.gulflens.presentation

import com.nyuad.gulflens.data.CompanyCatalog
import com.nyuad.gulflens.domain.RouteRules
import com.nyuad.gulflens.domain.StartupScenarioFactory
import com.nyuad.gulflens.model.AssessmentTrack
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.Product
import com.nyuad.gulflens.model.StartupProfileAnswers
import com.nyuad.gulflens.model.StartupScaleAnswers

/** The six user-visible stages shared by the Android and later Web clients. */
enum class AssessmentScreen {
    LANDING,
    TRACK,
    PROFILE,
    SCENARIO,
    ANALYSIS,
    RESULTS,
}

/**
 * Platform-neutral UI state for the complete GulfLens assessment.
 *
 * This deliberately contains IDs and domain values rather than Android types,
 * so the same state machine can be reused by the Web target.
 */
data class AssessmentState(
    val screen: AssessmentScreen = AssessmentScreen.LANDING,
    val track: AssessmentTrack = AssessmentTrack.COMPANY,
    val companyId: String = "cava",
    val productId: String = "build-your-own-bowls-pitas",
    val entryMode: EntryMode = EntryMode.LOCAL,
    val startupProfile: StartupProfileAnswers = StartupProfileAnswers(),
    val startupScale: StartupScaleAnswers = StartupScaleAnswers(),
) {
    val canNavigateBack: Boolean
        get() = screen != AssessmentScreen.LANDING

    fun navigateBack(): AssessmentState = copy(
        screen = when (screen) {
            AssessmentScreen.LANDING -> AssessmentScreen.LANDING
            AssessmentScreen.TRACK -> AssessmentScreen.LANDING
            AssessmentScreen.PROFILE -> AssessmentScreen.TRACK
            AssessmentScreen.SCENARIO -> AssessmentScreen.PROFILE
            AssessmentScreen.ANALYSIS,
            AssessmentScreen.RESULTS -> AssessmentScreen.SCENARIO
        },
    )

    fun selectTrack(nextTrack: AssessmentTrack): AssessmentState = when (nextTrack) {
        AssessmentTrack.COMPANY -> selectCompany("cava").copy(
            track = nextTrack,
            screen = AssessmentScreen.PROFILE,
        )

        AssessmentTrack.STARTUP -> copy(
            track = nextTrack,
            screen = AssessmentScreen.PROFILE,
            startupProfile = StartupProfileAnswers(),
            startupScale = StartupScaleAnswers(),
        )
    }

    fun selectCompany(nextCompanyId: String): AssessmentState {
        val company = requireNotNull(CompanyCatalog.findCompany(nextCompanyId))
        val product = company.products.first()
        return copy(
            companyId = company.id,
            productId = product.id,
            entryMode = requireNotNull(RouteRules.firstSelectableMode(product)),
        )
    }

    fun selectProduct(nextProductId: String): AssessmentState {
        val product = requireNotNull(CompanyCatalog.findProduct(companyId, nextProductId))
        val nextMode = entryMode.takeIf { product.availabilityFor(it).isSelectable }
            ?: requireNotNull(RouteRules.firstSelectableMode(product))
        return copy(productId = product.id, entryMode = nextMode)
    }

    fun selectEntryMode(nextMode: EntryMode): AssessmentState {
        require(activeProduct().availabilityFor(nextMode).isSelectable) {
            "The selected entry route is not available for this product."
        }
        return copy(entryMode = nextMode)
    }

    fun activeProduct(): Product = when (track) {
        AssessmentTrack.COMPANY -> requireNotNull(
            CompanyCatalog.findProduct(companyId, productId),
        )

        AssessmentTrack.STARTUP -> StartupScenarioFactory.create(startupProfile).product
    }

    fun profileName(): String = when (track) {
        AssessmentTrack.COMPANY -> requireNotNull(CompanyCatalog.findCompany(companyId)).name
        AssessmentTrack.STARTUP -> StartupScenarioFactory.create(startupProfile).profileName
    }
}
