package dev.skgopnik.ideaTelegramBot.commands

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId

fun CommandHandlerEnvironment.helpCommand() {
    bot.sendMessage(
        chatId = ChatId.fromId(message.chat.id),
        text = "Suck some dick"
    )
}