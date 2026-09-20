package com.nyuad.gulflens

import androidx.compose.runtime.Composable

@Composable
actual fun GulfLensBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    // Desktop windows own their close behavior; in-page Back buttons remain available.
}
