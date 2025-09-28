package com.example.vehiclecompanion.presentation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val space_0_25: Dp = 2.dp,
    val space_0_5: Dp = 4.dp,
    val space_1: Dp = 8.dp,
    val space_1_5: Dp = 12.dp,
    val space_2: Dp = 16.dp,
    val minimum_touch_target: Dp = 48.dp
)

val smallDimensions = Dimensions(
    space_0_25 = 1.5f.dp,
    space_0_5 = 3.dp,
    space_1 = 6.dp,
    space_1_5 = 9.dp,
    space_2 = 12.dp,
    minimum_touch_target = 40.dp,
)

val sw360Dimensions = Dimensions(
    space_0_25 = 2.dp,
    space_0_5 = 4.dp,
    space_1 = 8.dp,
    space_1_5 = 12.dp,
    space_2 = 16.dp,
    minimum_touch_target = 48.dp,
)

val sw600Dimensions = Dimensions(
    space_0_25 = 4.dp,
    space_0_5 = 8.dp,
    space_1 = 12.dp,
    space_1_5 = 16.dp,
    space_2 = 20.dp,
    minimum_touch_target = 48.dp,
)

val sw940Dimensions = Dimensions(
    space_0_25 = 5.dp,
    space_0_5 = 10.dp,
    space_1 = 15.dp,
    space_2 = 20.dp,
    minimum_touch_target = 48.dp,
)