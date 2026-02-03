package com.sentysingh.patient_service.mapper;

import com.sentysingh.patient_service.dto.PatientResponseDTO;
import com.sentysingh.patient_service.model.Patient;

// mapper function to convert model into a dto
public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDTO;
    }
}
