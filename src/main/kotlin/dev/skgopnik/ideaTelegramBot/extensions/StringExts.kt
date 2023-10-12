package dev.skgopnik.ideaTelegramBot.extensions

import java.util.*

fun String.toUUIDOrNull(): UUID? = try {
    UUID.fromString(this)
} catch(ex: Exception) {
    null
}
