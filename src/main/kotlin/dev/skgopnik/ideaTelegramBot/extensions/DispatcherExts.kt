package dev.skgopnik.ideaTelegramBot.extensions

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.handlers.CallbackQueryHandlerEnvironment
import kotlinx.serialization.json.Json

inline fun <reified T : Any> Dispatcher.callbackQuery(
    crossinline action: suspend CallbackQueryHandlerEnvironment.(T) -> Unit
): Unit = callbackQuery handle@{

    val data = try {
        Json.Default.decodeFromString<T>(callbackQuery.data)
    } catch (ex: Exception) {
        return@handle
    }

    action(data)

}
