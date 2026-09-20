package com.nyuad.gulflens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyuad.gulflens.i18n.AppLanguage
import com.nyuad.gulflens.i18n.Strings
import com.nyuad.gulflens.i18n.TextKey
import com.nyuad.gulflens.model.CatalogOption
import com.nyuad.gulflens.ui.components.GulfLensPrimaryButton
import com.nyuad.gulflens.ui.components.GulfLensSecondaryButton
import com.nyuad.gulflens.ui.theme.GulfLensSpacing

@Composable
internal fun OptionPicker(
    title: String,
    hint: String,
    placeholder: String,
    options: List<CatalogOption>,
    selectedId: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dismissText: String = "Close",
    optionLabel: (CatalogOption) -> String = { it.label },
) {
    var showDialog by remember { mutableStateOf(false) }
    val selected = options.firstOrNull { it.id == selectedId }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier.padding(GulfLensSpacing.Medium),
            verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Small),
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(
                hint,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            OutlinedButton(
                onClick = { showDialog = true },
                enabled = enabled,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
            ) {
                Text(
                    text = selected?.let(optionLabel) ?: placeholder,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text("⌄", style = MaterialTheme.typography.titleMedium)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(title, style = MaterialTheme.typography.headlineSmall) },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 430.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(options, key = { it.id }) { option ->
                        TextButton(
                            onClick = {
                                onSelected(option.id)
                                showDialog = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics { this.selected = option.id == selectedId },
                        ) {
                            Text(
                                optionLabel(option),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                color = if (option.id == selectedId) {
                                    MaterialTheme.colorScheme.secondary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                },
                            )
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(dismissText)
                }
            },
        )
    }
}

@Composable
internal fun <T> ResponsiveGrid(
    items: List<T>,
    modifier: Modifier = Modifier,
    twoColumnAt: Dp = 760.dp,
    content: @Composable (T) -> Unit,
) {
    BoxWithConstraints(modifier.fillMaxWidth()) {
        if (maxWidth >= twoColumnAt) {
            Column(verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium)) {
                items.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium),
                        verticalAlignment = Alignment.Top,
                    ) {
                        rowItems.forEach { item ->
                            Box(modifier = Modifier.weight(1f)) { content(item) }
                        }
                        if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium)) {
                items.forEach { item -> content(item) }
            }
        }
    }
}

@Composable
internal fun ScreenActions(
    language: AppLanguage,
    onBack: () -> Unit,
    primaryText: String? = null,
    onPrimary: (() -> Unit)? = null,
    primaryEnabled: Boolean = true,
    status: String? = null,
) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        if (maxWidth < 600.dp) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(GulfLensSpacing.Small),
            ) {
                if (!status.isNullOrBlank()) {
                    Text(
                        status,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (primaryText != null && onPrimary != null) {
                    GulfLensPrimaryButton(
                        text = primaryText,
                        onClick = onPrimary,
                        enabled = primaryEnabled,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                GulfLensSecondaryButton(
                    text = "${if (language.isRightToLeft) "→" else "←"} ${Strings.get(TextKey.BACK, language)}",
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(GulfLensSpacing.Medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GulfLensSecondaryButton(
                    text = "${if (language.isRightToLeft) "→" else "←"} ${Strings.get(TextKey.BACK, language)}",
                    onClick = onBack,
                )
                Spacer(Modifier.weight(1f))
                if (!status.isNullOrBlank()) {
                    Text(
                        status,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (primaryText != null && onPrimary != null) {
                    GulfLensPrimaryButton(
                        text = primaryText,
                        onClick = onPrimary,
                        enabled = primaryEnabled,
                    )
                }
            }
        }
    }
}

@Composable
internal fun ConversationBubble(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
) {
    Box(modifier = modifier.padding(bottom = 7.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            color = color.copy(alpha = 0.10f),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.32f)),
        ) {
            Text(
                text,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 13.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 22.dp, y = 6.dp)
                .size(14.dp)
                .rotate(45f)
                .border(1.dp, color.copy(alpha = 0.32f), RoundedCornerShape(3.dp))
                .background(color.copy(alpha = 0.10f), RoundedCornerShape(3.dp)),
        ) {}
    }
}

@Composable
internal fun NumberBadge(number: Int) {
    Surface(
        modifier = Modifier.size(38.dp),
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                number.toString().padStart(2, '0'),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
internal fun LanguageMenu(
    current: AppLanguage,
    onSelect: (AppLanguage) -> Unit,
    accessibilityLabel: String,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        TextButton(
            onClick = { expanded = true },
            modifier = Modifier.semantics { contentDescription = accessibilityLabel },
        ) {
            Text(current.displayName, style = MaterialTheme.typography.labelMedium)
            Text("  ⌄")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Strings.supportedLanguages.forEach { language ->
                DropdownMenuItem(
                    text = { Text(language.displayName) },
                    modifier = Modifier.semantics { this.selected = language == current },
                    onClick = {
                        onSelect(language)
                        expanded = false
                    },
                )
            }
        }
    }
}
