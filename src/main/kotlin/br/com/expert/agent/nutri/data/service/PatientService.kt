package br.com.expert.agent.nutri.data.service

import br.com.expert.agent.nutri.data.model.Patient
import br.com.expert.agent.nutri.data.repository.PatientRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class PatientService(
    private val patientRepository: PatientRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(PatientService::class.java)

    fun findAll(): Result<List<Patient>> = runCatching {
        patientRepository.findAll()
    }.onFailure {exception ->
        logger.error("Unexpected error fetching patients: ${exception.message}", exception)
    }

    fun findById(id: String): Result<Patient?> = runCatching {
        patientRepository.findById(id).getOrNull()
    }.onFailure {exception ->
        logger.error("Unexpected error fetching patient: ${exception.message}", exception)
    }

    fun findByNome(nome: String): Result<List<Patient>> = runCatching {
        patientRepository.findByNome(nome)
    }.onFailure {exception ->
        logger.error("Unexpected error fetching patient: ${exception.message}", exception)
    }

    @Transactional
    fun save(patient: Patient): Result<Patient> = runCatching {
        patientRepository.save(patient)
    }.onFailure { exception ->
        logger.error("Unexpected error saving patient: ${exception.message}", exception)
    }

    @Transactional
    fun delete(id: String): Result<Unit> = runCatching {
        patientRepository.deleteById(id)
    }.onFailure {exception ->
        logger.error("Unexpected error deleting patient: ${exception.message}", exception)
    }

    @Transactional
    fun update(id: String, patient: Patient): Result<Patient?> = runCatching {
        patientRepository.findById(id).getOrNull()
    }.onSuccess { patientFound ->
        patientFound.apply {
            Patient(
                nome = patient.nome,
                idade = patient.idade,
                peso = patient.peso,
                altura = patient.altura
            )
        }.also { updatedPatient ->
            updatedPatient?.let { patientRepository.save(updatedPatient) }
        }
    }.onFailure {exception ->
        logger.error("Unexpected error updating patient: ${exception.message}", exception)
    }


    @Transactional
    fun patialUpdate(id: String, patient: Patient): Result<Patient?> = runCatching {
        patientRepository.findById(id).getOrNull()
    }.onSuccess { patientFound ->
        patientFound.apply {
            Patient(
                nome = patient.nome ?: patientFound?.nome,
                idade = patient.idade ?: patientFound?.idade,
                peso = patient.peso ?: patientFound?.peso,
                altura = patient.altura ?: patientFound?.altura
            )
        }.also { updatedPatient ->
            updatedPatient?.let { patientRepository.save(updatedPatient) }
        }
    }.onFailure {exception ->
        logger.error("Unexpected error updating patient: ${exception.message}", exception)
    }
}
