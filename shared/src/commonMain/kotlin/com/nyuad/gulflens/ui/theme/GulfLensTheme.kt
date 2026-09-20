package com.nyuad.gulflens.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** GulfLens' platform-neutral brand palette. */
object GulfLensColors {
    val Navy = Color(0xFF0C2730)
    val DeepNavy = Color(0xFF0C3C46)
    val Teal = Color(0xFF126F78)
    val GreenTeal = Color(0xFF1B7C66)
    val Blue = Color(0xFF21476F)

    val Mist = Color(0xFFF2F6F4)
    val SoftMist = Color(0xFFE5EEEA)
    val Surface = Color(0xFFFCFEFD)
    val SoftSurface = Color(0xFFEDF4F1)
    val PaleTeal = Color(0xFFD8EDEB)
    val MutedInk = Color(0xFF5C7074)
    val Outline = Color(0xFF6F8986)

    val DarkBackground = Color(0xFF07171D)
    val DarkSurface = Color(0xFF122A32)
    val DarkSoftSurface = Color(0xFF18343D)
    val DarkInk = Color(0xFFF2F7F4)
    val LightTeal = Color(0xFF6ED2C3)
    val DarkAccent = Color(0xFF62CDD0)
    val DarkGreenTeal = Color(0xFF68D1AE)
    val DarkBlue = Color(0xFF80ADD8)

    val Error = Color(0xFFA63E42)
    val DarkError = Color(0xFFFFB4AB)
}

/** Shared layout values used by the reusable page components. */
object GulfLensSpacing {
    val XSmall = 4.dp
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
    val XLarge = 32.dp
    val Page = 24.dp
    val Card = 24.dp
    val MaxContentWidth = 1200.dp
}

private val LightColorScheme = lightColorScheme(
    primary = GulfLensColors.DeepNavy,
    onPrimary = Color.White,
    primaryContainer = GulfLensColors.PaleTeal,
    onPrimaryContainer = GulfLensColors.Navy,
    secondary = GulfLensColors.Teal,
    onSecondary = Color.White,
    secondaryContainer = GulfLensColors.SoftMist,
    onSecondaryContainer = GulfLensColors.Navy,
    tertiary = GulfLensColors.Blue,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFDCE8F3),
    onTertiaryContainer = GulfLensColors.Blue,
    background = GulfLensColors.Mist,
    onBackground = GulfLensColors.Navy,
    surface = GulfLensColors.Surface,
    onSurface = GulfLensColors.Navy,
    surfaceVariant = GulfLensColors.SoftSurface,
    onSurfaceVariant = GulfLensColors.MutedInk,
    outline = GulfLensColors.Outline,
    outlineVariant = GulfLensColors.SoftMist,
    error = GulfLensColors.Error,
    onError = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = GulfLensColors.LightTeal,
    onPrimary = GulfLensColors.DarkBackground,
    primaryContainer = Color(0xFF174B50),
    onPrimaryContainer = GulfLensColors.DarkInk,
    secondary = GulfLensColors.DarkAccent,
    onSecondary = GulfLensColors.DarkBackground,
    secondaryContainer = GulfLensColors.DarkSoftSurface,
    onSecondaryContainer = GulfLensColors.DarkInk,
    tertiary = GulfLensColors.DarkBlue,
    onTertiary = GulfLensColors.DarkBackground,
    tertiaryContainer = Color(0xFF1A3854),
    onTertiaryContainer = GulfLensColors.DarkInk,
    background = GulfLensColors.DarkBackground,
    onBackground = GulfLensColors.DarkInk,
    surface = GulfLensColors.DarkSurface,
    onSurface = GulfLensColors.DarkInk,
    surfaceVariant = GulfLensColors.DarkSoftSurface,
    onSurfaceVariant = Color(0xFFB9C9C8),
    outline = Color(0xFF78959A),
    outlineVariant = Color(0xFF29444C),
    error = GulfLensColors.DarkError,
    onError = Color(0xFF690005),
)

private val GulfLensTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 56.sp,
        lineHeight = 62.sp,
        letterSpacing = 0.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 46.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
        lineHeight = 46.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 38.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        lineHeight = 32.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 29.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 25.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 26.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 23.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 19.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 17.sp,
    ),
)

private val GulfLensShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(26.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
)

@Composable
fun GulfLensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = GulfLensTypography,
        shapes = GulfLensShapes,
        content = content,
    )
}
