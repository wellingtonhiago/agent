package br.com.expert.agent.nutri.data.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Patients")
data class Patient(
    @Id
    val id: ObjectId? = null,
    val nome: String?,
    val idade: Int?,
    val peso: Double?,
    val altura: Double?,
    val objetivo: String?
)
