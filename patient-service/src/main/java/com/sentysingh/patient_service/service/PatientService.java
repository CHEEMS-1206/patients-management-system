package com.sentysingh.patient_service.service;

import com.sentysingh.patient_service.dto.PatientRequestDTO;
import com.sentysingh.patient_service.exception.EmailAlreadyExistsException;
import com.sentysingh.patient_service.exception.PatientNotFoundException;
import com.sentysingh.patient_service.mapper.PatientMapper;
import com.sentysingh.patient_service.model.Patient;
import com.sentysingh.patient_service.dto.PatientResponseDTO;
import com.sentysingh.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    // constructor injection
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    // service layer passing DTO to controller after converting model to DTO
    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        // using mapper to map normal model into a dto
        List<PatientResponseDTO> patientResponseDTOS = patients.stream()
                .map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOS;
    }

    // service layer passing model to repository after converting DTO to model
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) throws EmailAlreadyExistsException {
        if(!patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
            return PatientMapper.toDTO(newPatient);
        }
        throw new EmailAlreadyExistsException("A patient already exists in the system with following email " + patientRequestDTO.getEmail() + " !");
    }

    // update patients
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with the Id : " + id + " !"));
        if(!patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            patient.setName(patientRequestDTO.getName());
            patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
            patient.setEmail(patientRequestDTO.getEmail());
            patient.setAddress(patientRequestDTO.getAddress());

            Patient updatedPatient = patientRepository.save(patient);
            return PatientMapper.toDTO(updatedPatient);
        }
        throw new EmailAlreadyExistsException("A patient already exists in the system with following email " + patientRequestDTO.getEmail() + " !");
    }
}
