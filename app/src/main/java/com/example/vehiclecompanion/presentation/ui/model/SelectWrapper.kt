package com.example.vehiclecompanion.presentation.ui.model

data class SelectWrapper<T: MenuOption>(
    val options: List<T> = emptyList(),
    val hasInteracted: Boolean = false,
    val required: Boolean = true,
    val error: TextResource? = null
) {
    val selected: T?
        get() = options.firstOrNull { it.checked }

    val selectedIndex: Int
        get() = options.indexOfFirst { it.checked }

    val hasError: Boolean
        get() = if (required) {
            hasInteracted && error != null
        } else {
            error != null
        }

    val isValid: Boolean
        get() = if (required) {
            hasInteracted && error == null
        } else error == null
}