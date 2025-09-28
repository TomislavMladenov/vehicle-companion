package com.example.vehiclecompanion.presentation.ui.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface TextResource

data class DynamicString(val text: String) : TextResource
data class ResourceString(@param:StringRes val resource: Int) : TextResource

@Composable
fun textResource(resource: TextResource): String = when (resource) {
    is DynamicString -> resource.text
    is ResourceString -> stringResource(resource.resource)
}

@Composable
fun textResourceOrEmpty(resource: TextResource?): String = when (resource) {
    is DynamicString -> resource.text
    is ResourceString -> stringResource(resource.resource)
    else -> ""
}