package com.sentysingh.patient_service.controller;

import com.sentysingh.patient_service.dto.PatientRequestDTO;
import com.sentysingh.patient_service.dto.PatientResponseDTO;
import com.sentysingh.patient_service.dto.validators.CreatePatientValidationGroup;
import com.sentysingh.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Patient", description = "API for managing patients in the Patients-management-system.")
// Using SWAGGER to craete documentation, move to swaggerui to get the ui version. @ http://localhost:4000/v3/api-docs
@RequestMapping("/patients")
public class PatientController {
    // dependency injection
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // get all patients method
    @GetMapping
    @Operation(summary = "Get all patients !")
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    // post mapping to add new patient
    @PostMapping
    @Operation(summary = "Add a new patient !")
    // using @validated to validate all points that are in default validation class and the CreatePatientValidationGroup class as well, so that we can validate registered date as well
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    // update patient details
    @PutMapping("/{id}")
    @Operation(summary = "Update patient details !")
    // we do not need to validate registered date hence not used -> CreatePatientValidationGroup
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    // deleting patients
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient !")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
