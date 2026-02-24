package com.design.ripple

import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun circleRipple(dp: Dp = 18.dp) = ripple(
    color = Color.Gray,
    bounded = false,
    radius = dp
)