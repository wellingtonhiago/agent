package br.com.expert.agent.nutri.repository

import br.com.expert.agent.nutri.model.Patient
import org.springframework.data.mongodb.repository.MongoRepository

interface PatientRepository : MongoRepository<Patient, String> {
}