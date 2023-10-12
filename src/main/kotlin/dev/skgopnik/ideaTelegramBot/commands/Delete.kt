package dev.skgopnik.ideaTelegramBot.commands

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import dev.skgopnik.ideaTelegramBot.extensions.reply
import dev.skgopnik.ideaTelegramBot.extensions.toUUIDOrNull
import dev.skgopnik.ideaTelegramBot.models.Ideas
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun CommandHandlerEnvironment.deleteCommand() {

    if (args.isEmpty()) {
        reply("Stupid monke?")
        return
    }

    val ideaId = args.first()
        .toIntOrNull()
        ?: run {
            reply("Dolboeb!")
            return
        }

    val affectedRows = newSuspendedTransaction(Dispatchers.IO) {
        Ideas.deleteWhere { Ideas.id eq ideaId }
    }

    if (affectedRows == 0) {
        reply("Nihuya netu")
        return
    }

    bot.sendMessage(
        chatId = ChatId.fromId(message.chat.id),
        text = "Idea with id - $ideaId deleted!"
    )
}
