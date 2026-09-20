package com.nyuad.gulflens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun GulfLensBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    BackHandler(enabled = enabled, onBack = onBack)
}
