package dev.skgopnik.ideaTelegramBot.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable

object Ideas : IntIdTable() {
    val text = text("name")
}
