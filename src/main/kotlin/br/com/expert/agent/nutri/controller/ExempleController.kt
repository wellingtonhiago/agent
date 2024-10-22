package br.com.expert.agent.nutri.controller

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class Controller(
    private val chatClient: ChatClient,
    private val openAiChatModel: OpenAiChatModel
) {

    private val userImput = "Hello, World!"

    @GetMapping("/exemple")
    private fun generation() =
        this.chatClient.prompt()
            .user(userImput) //define o conteúdo da mensagem
            .call() //envia uma solicitação (request) ao modelo de IA
            .content() //retorna (response) o conteúdo como uma string

    @GetMapping("/metadata")
    private fun response() =
        this.chatClient.prompt()
            .user(userImput)
            .call()
            .chatResponse() // retorna o objeto ChatResponse que contém os metadados

    // Retorna uma entidade mapeada a partir da string retornada pelo modelo de IA
    @GetMapping("/entity")
    private fun actor() =
        this.chatClient.prompt()
            .user("Gere a filmografia de um ator aleatório.")
            .call()
            .entity(ActorFilms::class.java)

    @GetMapping("/stream")
    private fun stream() =
        this.chatClient.prompt()
            .user("Conte-me uma piada.")
            .stream()
            .content()

    @GetMapping("/simple")
    private fun completion(
        @RequestParam(value = "prompt", defaultValue = "Conte-me uma piada.") prompt: String,
        ): Map<String, String> =
        mapOf(
        "completion" to this.chatClient.prompt()
            .user(prompt)
            .call()
            .content()
        )

    //Exemplo passando uma voz em tempo de execução
    @GetMapping("/voice")
    private fun completionVoice(
        @RequestParam(value = "prompt", defaultValue = "Conte-me uma piada.") prompt: String, voice: String,
    ): Map<String, String> =
        mapOf(
            "completion" to this.chatClient.prompt()
                .system { sp -> sp.param("voice", voice) }
                .user(prompt)
                .call()
                .content()
        )


}

data class ActorFilms(
    val name: String,
    val films: List<String>,
)

