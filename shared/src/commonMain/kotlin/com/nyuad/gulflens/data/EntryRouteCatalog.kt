package com.nyuad.gulflens.data

import com.nyuad.gulflens.model.EntryMode
import com.nyuad.gulflens.model.EntryRoute

object EntryRouteCatalog {
    val routes: List<EntryRoute> = listOf(
        EntryRoute(
            mode = EntryMode.REMOTE,
            title = "Remote / digital sales",
            description = "Serve UAE customers digitally without importing physical goods or establishing a local operating footprint.",
        ),
        EntryRoute(
            mode = EntryMode.IMPORT,
            title = "Import, distribute & sell",
            description = "Bring finished physical products into the UAE through an approved importer, distributor and sales channel.",
        ),
        EntryRoute(
            mode = EntryMode.LOCAL,
            title = "Local operations or production + sales",
            description = "Operate stores, services, assembly or production in the UAE through an appropriate local structure.",
        ),
    )

    fun forMode(mode: EntryMode): EntryRoute = routes.first { it.mode == mode }
}
