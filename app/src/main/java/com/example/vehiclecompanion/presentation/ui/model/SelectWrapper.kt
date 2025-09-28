package com.example.vehiclecompanion.presentation.ui.model

data class SelectWrapper<T: MenuOption>(
    val options: List<T> = emptyList(),
    val required: Boolean = true,
    val error: TextResource? = null,
    val enabled: Boolean = true
) {
    val selected: T?
        get() = options.firstOrNull { it.checked }

    val selectedIndex: Int
        get() = options.indexOfFirst { it.checked }

    val isValid: Boolean
        get() = if (required) {
            selected != null && error == null
        } else error == null

    val hasError: Boolean
        get() = if (required) {
            selected != null && error != null
        } else {
            error != null
        }

    val canInteract: Boolean
        get() = enabled && options.isNotEmpty()
}