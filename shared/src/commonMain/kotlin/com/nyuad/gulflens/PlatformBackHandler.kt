package com.nyuad.gulflens

import androidx.compose.runtime.Composable

/** Platform hook that keeps Android's system Back aligned with the shared state machine. */
@Composable
expect fun GulfLensBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
)
