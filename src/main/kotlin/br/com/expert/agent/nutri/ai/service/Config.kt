package br.com.expert.agent.nutri.ai.service

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.ChatClient.Builder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    //Cria um ChatClient com texto do sistema padrão em uma classe anotada com @Configuration
//    @Bean
//    fun chatClient(builder: Builder): ChatClient =
//        builder.defaultSystem("Você é um chatbot amigável que responde perguntas na voz de um Pirata")
//            .build()

    //Aqui definimos o padrão com o espaço reservado no texto do sistema para especificar a voz em tempo de execução
    @Bean
    fun chatClient(builder: Builder): ChatClient =
        builder.defaultSystem("Você é um chatbot amigável que responde às perguntas com a voz de um {voice}")
            .build()

}