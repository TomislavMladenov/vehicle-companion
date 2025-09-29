package com.example.vehiclecompanion.presentation.ui.components.atoms.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vehiclecompanion.presentation.ui.theme.Dimens
import com.example.vehiclecompanion.presentation.ui.theme.VCDeepBlue

@Composable
fun VCRoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth(.8f)
            .height(48.dp)
            .padding(horizontal = Dimens.space_0_5),
        colors = ButtonDefaults.buttonColors(
            containerColor = VCDeepBlue
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}