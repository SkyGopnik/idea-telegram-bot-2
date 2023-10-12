package dev.skgopnik.ideaTelegramBot.callbacks

import com.github.kotlintelegrambot.dispatcher.handlers.CallbackQueryHandlerEnvironment
import dev.skgopnik.ideaTelegramBot.actions.Action
import dev.skgopnik.ideaTelegramBot.extensions.reply

fun CallbackQueryHandlerEnvironment.editCallback(action: Action.Edit) {
    reply("Введи новую идею:")
}
