package br.com.expert.agent.nutri.data.controller

import br.com.expert.agent.nutri.data.model.Patient
import br.com.expert.agent.nutri.data.service.PatientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/patients")
class PatientController(
    val patientService: PatientService,
) {

    @PostMapping
    fun save(@RequestBody patient: Patient): ResponseEntity<Patient> =
        ResponseEntity.ok(patientService.save(patient).getOrNull())

    @GetMapping
    fun findAll(): ResponseEntity<List<Patient>> =
        ResponseEntity.ok(patientService.findAll().getOrNull())

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Patient?> =
        ResponseEntity.ok(patientService.findById(id).getOrNull())

    @GetMapping("/{nome}")
    fun findByNome(@PathVariable("nome") name: String): ResponseEntity<List<Patient>> =
        ResponseEntity.ok(patientService.findByNome(name).getOrNull())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Unit> =
        ResponseEntity.ok(patientService.delete(id).getOrNull())

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody patient: Patient): ResponseEntity<Patient> =
        ResponseEntity.ok(patientService.patialUpdate(id, patient).getOrNull())

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable("id") id: String, @RequestBody patient: Patient): ResponseEntity<Patient> =
        ResponseEntity.ok(patientService.patialUpdate(id, patient).getOrNull())

}