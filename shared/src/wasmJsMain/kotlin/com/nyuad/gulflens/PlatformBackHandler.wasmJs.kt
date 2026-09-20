package com.nyuad.gulflens

import androidx.compose.runtime.Composable

@Composable
actual fun GulfLensBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    // Navigation inside the web app uses the shared in-page Back controls.
}
