package dev.skgopnik.ideaTelegramBot.extensions

import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Any> createCallbackDataButton(
    text: String,
    data: T
) = InlineKeyboardButton.CallbackData(
    text = text,
    callbackData = Json.Default.encodeToString(data)
)
