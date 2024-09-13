package br.com.expert.agent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AgentApplication

fun main(args: Array<String>) {
	runApplication<AgentApplication>(*args)
}
