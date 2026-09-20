package com.nyuad.gulflens.data

import com.nyuad.gulflens.model.CompanyProfile
import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.Product
import com.nyuad.gulflens.model.RouteAvailability

object CompanyCatalog {
    val companies: List<CompanyProfile> = listOf(
        CompanyProfile(
            id = "cava",
            name = "CAVA",
            description = "Mediterranean fast-casual restaurant brand serving customizable bowls and pitas, with dips, spreads and dressings also sold through grocery.",
            location = "Washington, D.C. headquarters · Restaurant locations across the United States",
            tags = listOf("Restaurants", "Consumer food"),
            products = listOf(
                product(
                    id = "build-your-own-bowls-pitas",
                    name = "Build-your-own bowls & pitas",
                    description = "Customizable restaurant offer assembled to order; requires local kitchens, food-service licensing, trained staff and local sales.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.UNAVAILABLE,
                    localRoute = RouteAvailability.AVAILABLE,
                ),
                product(
                    id = "chef-curated-bowls-pitas",
                    name = "Chef-curated bowls & pitas",
                    description = "Chef-designed menu combinations prepared and sold through a locally operated restaurant network.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.UNAVAILABLE,
                    localRoute = RouteAvailability.AVAILABLE,
                ),
                product(
                    id = "catering-group-bowl-bar",
                    name = "Catering / Group Bowl Bar",
                    description = "Group ordering and catering service fulfilled from local kitchens for offices, events and larger gatherings.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.UNAVAILABLE,
                    localRoute = RouteAvailability.AVAILABLE,
                ),
            ),
        ),
        CompanyProfile(
            id = "warby-parker",
            name = "Warby Parker",
            description = "Omnichannel eyewear and vision-care company offering prescription glasses, sunglasses, contact lenses, eye exams and digital vision tools.",
            location = "New York City headquarters · Retail stores across the United States and Canada",
            tags = listOf("Eyewear", "Omnichannel retail"),
            products = listOf(
                product(
                    id = "prescription-eyeglasses",
                    name = "Prescription eyeglasses",
                    description = "Corrective eyewear requiring prescription validation, product compliance, optical dispensing and potentially licensed local eye-care support.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.CONDITIONAL,
                    localRoute = RouteAvailability.CONDITIONAL,
                ),
                product(
                    id = "non-prescription-sunglasses",
                    name = "Non-prescription sunglasses",
                    description = "Consumer eyewear suited to e-commerce, wholesale or branded retail with standard product-compliance checks.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.AVAILABLE,
                    localRoute = RouteAvailability.AVAILABLE,
                ),
                product(
                    id = "contact-lenses",
                    name = "Contact lenses",
                    description = "Regulated vision-care products requiring approvals, prescription handling and controlled retail or clinical channels.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.CONDITIONAL,
                    localRoute = RouteAvailability.CONDITIONAL,
                ),
            ),
        ),
        CompanyProfile(
            id = "freshpet",
            name = "Freshpet",
            description = "Manufacturer, marketer and distributor of fresh dog food, cat food and dog treats sold through retail and distributor channels.",
            location = "Bedminster, New Jersey headquarters · Kitchens in Bethlehem, Pennsylvania and Ennis, Texas",
            tags = listOf("Pet food", "Cold chain"),
            products = listOf(
                product(
                    id = "refrigerated-dog-food-rolls",
                    name = "Refrigerated dog-food rolls",
                    description = "Fresh pet-food rolls requiring an uninterrupted cold chain, shelf-life controls and compliant product registration.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.CONDITIONAL,
                    localRoute = RouteAvailability.UNAVAILABLE,
                ),
                product(
                    id = "refrigerated-bagged-dog-meals",
                    name = "Refrigerated bagged dog meals",
                    description = "Ready-to-serve fresh dog meals dependent on temperature-controlled shipping, storage and refrigerated retail display.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.CONDITIONAL,
                    localRoute = RouteAvailability.UNAVAILABLE,
                ),
                product(
                    id = "refrigerated-cat-food",
                    name = "Refrigerated cat food",
                    description = "Fresh cat food with cold-chain, shelf-life and animal-feed compliance requirements throughout distribution.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.CONDITIONAL,
                    localRoute = RouteAvailability.UNAVAILABLE,
                ),
            ),
        ),
        CompanyProfile(
            id = "toast",
            name = "Toast",
            description = "Cloud-based, all-in-one technology platform for restaurants and food-and-beverage retailers, combining SaaS, integrated payments, financial technology and purpose-built hardware.",
            location = "Boston headquarters · Offices across North America, Europe and Asia",
            tags = listOf("Restaurant tech", "Software + hardware"),
            products = listOf(
                product(
                    id = "restaurant-pos-software",
                    name = "Restaurant POS software",
                    description = "Cloud-based point-of-sale, payments and restaurant-management software delivered digitally to operators.",
                    remote = RouteAvailability.AVAILABLE,
                    importRoute = RouteAvailability.UNAVAILABLE,
                    localRoute = RouteAvailability.CONDITIONAL,
                ),
                product(
                    id = "toast-go-3-handheld-platform",
                    name = "Toast Go 3 handheld + platform",
                    description = "Handheld restaurant ordering and payment device bundled with the Toast platform and payment services.",
                    remote = RouteAvailability.UNAVAILABLE,
                    importRoute = RouteAvailability.CONDITIONAL,
                    localRoute = RouteAvailability.CONDITIONAL,
                ),
                product(
                    id = "online-ordering-digital-storefront",
                    name = "Online Ordering & Digital Storefront",
                    description = "Digital ordering and branded storefront tools that connect restaurants directly with guests.",
                    remote = RouteAvailability.AVAILABLE,
                    importRoute = RouteAvailability.UNAVAILABLE,
                    localRoute = RouteAvailability.CONDITIONAL,
                ),
            ),
        ),
    )

    fun findCompany(id: String): CompanyProfile? = companies.firstOrNull { it.id == id }

    fun findProduct(companyId: String, productId: String): Product? =
        findCompany(companyId)?.products?.firstOrNull { it.id == productId }

    private fun product(
        id: String,
        name: String,
        description: String,
        remote: RouteAvailability,
        importRoute: RouteAvailability,
        localRoute: RouteAvailability,
    ) = Product(
        id = id,
        name = name,
        description = description,
        routeAvailability = mapOf(
            EntryMode.REMOTE to remote,
            EntryMode.IMPORT to importRoute,
            EntryMode.LOCAL to localRoute,
        ),
    )
}
