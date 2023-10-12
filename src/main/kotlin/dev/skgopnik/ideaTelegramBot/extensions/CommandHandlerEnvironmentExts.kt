package dev.skgopnik.ideaTelegramBot.extensions

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId

fun CommandHandlerEnvironment.reply(
    text: String
) = bot.sendMessage(
    chatId = ChatId.fromId(message.chat.id),
    text = text
)
