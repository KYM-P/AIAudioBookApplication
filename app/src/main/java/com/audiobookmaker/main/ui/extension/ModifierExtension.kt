package com.audiobookmaker.main.ui.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noEffectClickable(
    onClick: () -> Unit,
    enabled: Boolean = true
): Modifier = composed {
    clickable(
        enabled = enabled,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

fun Modifier.rippleClickable(
    onClick: () -> Unit,
    enabled: Boolean = true
): Modifier = composed {
    clickable(
        enabled = enabled,
        indication = ripple(),
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}