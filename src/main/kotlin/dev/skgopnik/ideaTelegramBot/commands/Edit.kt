package dev.skgopnik.ideaTelegramBot.commands

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import dev.skgopnik.ideaTelegramBot.extensions.reply
import dev.skgopnik.ideaTelegramBot.models.Ideas
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

suspend fun CommandHandlerEnvironment.editCommand() {

    if (args.size < 2) {
        reply("Stupid monke?")
        return
    }

    val ideaId = args.first()
        .toIntOrNull()
        ?: run {
            reply("Dolboeb!")
            return
        }

    val newMessage = args.drop(1).joinToString(" ")

    val updateRow = newSuspendedTransaction(Dispatchers.IO) {
        Ideas.update(
            where = { Ideas.id eq ideaId }
        ) {
            it[text] = newMessage
        }
    }

    if (updateRow == 0) {
        reply("Nixua ne obnovilos")
        return
    }

    bot.sendMessage(
        chatId = ChatId.fromId(message.chat.id),
        text = "Idea with id - $ideaId updated!"
    )
}