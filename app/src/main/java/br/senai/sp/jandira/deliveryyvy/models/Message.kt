package br.senai.sp.jandira.deliveryyvy.models

import java.util.*

data class Message(
    var text:String?= null,
    var recipient_id: String,
    var time: Long = Calendar.getInstance().timeInMillis,
    var isOut: Boolean = false
)

val message_dummy = listOf(

    Message(
        text = "tudo e vc?",
        recipient_id = "eu",
        isOut = false
    ),
    Message(
        text = "tudo bem?",
        recipient_id = "voce",
        isOut = true
    ),
    Message(
        text = "Oi...",
        recipient_id = "eu",
        isOut = false
    ),
    Message(
        text = "Oi",
        recipient_id = "voce",
        isOut = true
    )


)
