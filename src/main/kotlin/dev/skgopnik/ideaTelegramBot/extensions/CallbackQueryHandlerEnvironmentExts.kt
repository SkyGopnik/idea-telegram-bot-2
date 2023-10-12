package dev.skgopnik.ideaTelegramBot.extensions

import com.github.kotlintelegrambot.dispatcher.handlers.CallbackQueryHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId

fun CallbackQueryHandlerEnvironment.reply(
    text: String
) {
    val chatId = callbackQuery.message?.chat?.id ?: return

    bot.sendMessage(
        chatId = ChatId.fromId(chatId),
        text = text
    )
}
