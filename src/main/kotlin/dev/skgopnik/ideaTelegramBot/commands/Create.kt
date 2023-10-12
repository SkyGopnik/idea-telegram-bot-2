package dev.skgopnik.ideaTelegramBot.commands

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import dev.skgopnik.ideaTelegramBot.extensions.reply
import dev.skgopnik.ideaTelegramBot.models.Ideas
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun CommandHandlerEnvironment.createCommand() {

    if (args.isEmpty()) {
        reply("Stupid monke?")
        return
    }

    val insertedRow = newSuspendedTransaction(Dispatchers.IO) {
        Ideas.insert {
            it[text] = args.joinToString(" ")
        }
    }

    val ideaId = insertedRow[Ideas.id]

    bot.sendMessage(
        chatId = ChatId.fromId(message.chat.id),
        text = "Idea with id - $ideaId created!"
    )
}