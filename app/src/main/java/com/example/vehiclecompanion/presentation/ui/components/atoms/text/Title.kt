package com.example.vehiclecompanion.presentation.ui.components.atoms.text

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun ScreenTitle(
    title: @Composable () -> Unit
) {
    ProvideTextStyle(
        value = MaterialTheme.typography.headlineSmall.copy(
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both
            )
        )
    ) {
        title()
    }
}

@Composable
fun VCScreenTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    ScreenTitle {
        VCScreenTitleText(
            modifier = modifier,
            title = title
        )
    }
}

@Composable
fun VCScreenTitleText(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier,
        text = title,
        color = Color.Black
    )
}
