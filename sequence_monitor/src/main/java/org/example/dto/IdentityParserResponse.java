package org.example.dto;

import org.example.enums.Citizenship;
import org.example.enums.Gender;

import java.time.LocalDate;

public class IdentityParserResponse {

    private String idNumber;
    private LocalDate birthDate;
    private int age;
    private Gender gender;
    private Citizenship citizenship;
    private String citizenshipDescription;
    private int obsoleteDigit;
    private int checksumDigit;
    private boolean checksumValid;
    private boolean valid;

    public IdentityParserResponse(){

    }

    public String getIdNumber(){
        return idNumber;
    }

    public void setIdNumber(
            String idNumber
    ) {
        this.idNumber = idNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(
            LocalDate birthDate
    ) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(
            Gender gender
    ) {
        this.gender = gender;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(
            Citizenship citizenship
    ) {
        this.citizenship = citizenship;
    }

    public String getCitizenshipDescription() {
        return citizenshipDescription;
    }

    public void setCitizenshipDescription(
            String citizenshipDescription
    ) {
        this.citizenshipDescription =
                citizenshipDescription;
    }

    public int getObsoleteDigit() {
        return obsoleteDigit;
    }

    public void setObsoleteDigit(
            int obsoleteDigit
    ) {
        this.obsoleteDigit = obsoleteDigit;
    }

    public int getChecksumDigit() {
        return checksumDigit;
    }

    public void setChecksumDigit(
            int checksumDigit
    ) {
        this.checksumDigit = checksumDigit;
    }

    public boolean isChecksumValid() {
        return checksumValid;
    }

    public void setChecksumValid(
            boolean checksumValid
    ) {
        this.checksumValid = checksumValid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(
            boolean valid
    ) {
        this.valid = valid;
    }

}
