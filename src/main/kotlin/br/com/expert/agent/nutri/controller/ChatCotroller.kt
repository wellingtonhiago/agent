package br.com.expert.agent.nutri.controller

import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatCotroller(
    private val chatModel: OpenAiChatModel
) {

    @RequestMapping("/ai/generate")
    fun generate(
        @RequestParam(value = "message", defaultValue = "Conte-me uma piada.")
        message: String
    ): Map<String, String> {
        return mapOf("generation" to chatModel.call(message))
    }
}