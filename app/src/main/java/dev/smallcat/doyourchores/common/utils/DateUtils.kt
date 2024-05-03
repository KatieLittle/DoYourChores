package dev.smallcat.doyourchores.common.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun formatDateLocally(
    date: LocalDate,
    formatStyle: FormatStyle,
    locale: Locale = Locale.getDefault()
) : String {
    val formatter = DateTimeFormatter
        .ofLocalizedDate( formatStyle )
        .withLocale( locale )

    return date.format(formatter)
}