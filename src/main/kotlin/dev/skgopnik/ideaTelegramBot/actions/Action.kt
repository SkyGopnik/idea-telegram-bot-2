package dev.skgopnik.ideaTelegramBot.actions

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
sealed interface Action {

    @Serializable
    @SerialName("EDIT")
    class Edit(
        val id: Int
    ) : Action

    @Serializable
    @SerialName("DELETE")
    class Delete(
        val id: Int
    ) : Action

}
