package dev.skgopnik.ideaTelegramBot.commands

import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import dev.skgopnik.ideaTelegramBot.actions.Action
import dev.skgopnik.ideaTelegramBot.extensions.createCallbackDataButton
import dev.skgopnik.ideaTelegramBot.extensions.reply
import dev.skgopnik.ideaTelegramBot.models.Ideas
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun CommandHandlerEnvironment.listCommand() {

    val selectedIdeas = newSuspendedTransaction(Dispatchers.IO) {
        Ideas.selectAll().toList()
    }
    
    if (selectedIdeas.isEmpty()) {
        reply("Nihuya netu, napishi po bratski")
        return
    }

    selectedIdeas.forEach {

        val id = it[Ideas.id].value

        bot.sendMessage(
            chatId = ChatId.fromId(message.chat.id),
            text = """
                $id.
                 ${it[Ideas.text]}
            """.trimIndent(),
            replyMarkup = InlineKeyboardMarkup.createSingleRowKeyboard(
                createCallbackDataButton(
                    text = "✏\uFE0F",
                    data = Action.Edit(id)
                ),
                createCallbackDataButton(
                    text = "\uD83D\uDDD1",
                    data = Action.Delete(id)
                )
            )
        )

    }
}
