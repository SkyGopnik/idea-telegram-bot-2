package dev.skgopnik.ideaTelegramBot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.handlers.CallbackQueryHandlerEnvironment
import dev.skgopnik.ideaTelegramBot.callbacks.editCallback
import dev.skgopnik.ideaTelegramBot.commands.*
import dev.skgopnik.ideaTelegramBot.models.Ideas
import dev.skgopnik.ideaTelegramBot.extensions.callbackQuery
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {

    Database.connect(
        url = "jdbc:postgresql://localhost/idea-telegram-bot",
        user = "postgres"
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Ideas)
    }

    println("Database connected")

    val bot = bot {

        token = "6042649656:AAFjAF4q1TNo_DnbLpb-gnNdCgfS6K21kvg"

        dispatch {

            command("help") { helpCommand() }
            command("list") { listCommand() }
            command("create") { createCommand() }
            command("edit") { editCommand() }
            command("delete") { deleteCommand() }

            callbackQuery(CallbackQueryHandlerEnvironment::editCallback)

        }

    }

    bot.startPolling()

    println("Bot connected")
}
