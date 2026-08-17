package org.example.domain;

import org.example.enums.Citizenship;
import org.example.enums.Gender;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Immutable domain Object containing the Information
 * Extracted from a South African ID
 *
 * No Spring, JPA, HTTP, database logic.
 */

public class ParsedIdentity {

    private final String idNumber;
    private final LocalDate birthDate;
    private final int age;
    private final Gender gender;
    private final Citizenship citizenship;
    private final int obsoleteDigit;
    private final int checksumDigit;
    private final boolean checksumValid;
    private final boolean valid;

    public ParsedIdentity(
            String idNumber,
            LocalDate birthDate,
            int age,
            Gender gender,
            Citizenship citizenship,
            int obsoleteDigit,
            int checksumDigit,
            boolean checksumValid,
            boolean valid
    ){
        this.idNumber = Objects.requireNonNull(
                idNumber,
                "ID number cannot be null"
        );

        this.birthDate = Objects.requireNonNull(
                birthDate,
                "Birth date cannot be null"
        );

        this.gender = Objects.requireNonNull(
                gender,
                "Gender cannot be null"
        );

        this.citizenship = Objects.requireNonNull(
                citizenship,
                "Citizenship cannot be null"
        );

        this.age = age;
        this.obsoleteDigit = obsoleteDigit;
        this.checksumDigit = checksumDigit;
        this.checksumValid = checksumValid;
        this.valid = valid;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public int getObsoleteDigit() {
        return obsoleteDigit;
    }

    public int getChecksumDigit() {
        return checksumDigit;
    }

    public boolean isChecksumValid() {
        return checksumValid;
    }

    public boolean isValid() {
        return valid;
    }

}
