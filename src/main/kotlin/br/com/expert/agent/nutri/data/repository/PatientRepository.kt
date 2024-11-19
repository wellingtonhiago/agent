package br.com.expert.agent.nutri.data.repository

import br.com.expert.agent.nutri.data.model.Patient
import org.springframework.data.mongodb.repository.MongoRepository

interface PatientRepository : MongoRepository<Patient, String> {

    fun findByNome(nome: String): List<Patient>

}
