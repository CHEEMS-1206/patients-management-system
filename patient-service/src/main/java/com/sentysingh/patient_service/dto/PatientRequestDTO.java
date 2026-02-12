package com.sentysingh.patient_service.dto;

import com.sentysingh.patient_service.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    @NotBlank(message = "Name field can not be blank !")
    @Size(max=100, message = "Name cannot have more than 100 characters !")
    private String name;

    @NotBlank(message = "Email field can not be blank !")
    @Email(message = "Please enter a valid email1 !")
    private String email;

    @NotBlank(message = "Address field can not be blank !")
    private String address;

    // not to default class but a separate implementation -> CreatePatientValidationGroup, rest other properties are in default class
    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered date is required !")
    private String registeredDate;

    @NotBlank(message = "Birth date is required !")
    private String dateOfBirth;

    // Getters and setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRegisteredDate() {
        return registeredDate;
    }
    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
