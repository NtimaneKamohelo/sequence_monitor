package org.example.parser;

import org.example.domain.ParsedIdentity;
import org.example.enums.Citizenship;
import org.example.enums.Gender;
import org.example.util.CenturyResolver;
import org.example.validation.LuhnValidator;
import org.example.validation.RegexValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.PrimitiveIterator;

/**
 * Main facade responsible for parsing and structurally
 * validating RSA ID number.
 *
 * Processing sequence:
 *
 * 1. Regex validation
 * 2. Date of birth parsing
 * 3. Gender parsing
 * 4. Citizenship parsing
 * 5. Obsolete digit extratction
 * 6. Luhn checksum validation
 * 7. Age calculation
 * 8. ParsedIdentity creation
 *
 */

public class SouthAfricanIdParser {

    private static final int OBSOLETE_DIGIT_INDEX = 11;
    private static final int CHECKSUM_INDEX = 12;
    private final RegexValidator regexValidator;
    private final DateofBirthParser dateofBirthParser;
    private final GenderParser genderParser;
    private final CitizenshipParser citizenshipParser;
    private final LuhnValidator luhnValidator;


    public SouthAfricanIdParser(){
        this.regexValidator = new RegexValidator();

        this.dateofBirthParser = new DateofBirthParser(
                new CenturyResolver()
        );

        this.genderParser = new GenderParser();
        this.citizenshipParser = new CitizenshipParser();
        this.luhnValidator = new LuhnValidator();
    }

    /**
     * Parses a South African identity number
     *
     * @param idNumber 13-digit RSA ID
     * @return fully parsed identity
     * @throws IllegalArgumentException when the ID structure
     * or encoded date/citizenship information is invalid
     */
    public ParsedIdentity parse(String idNumber){
        validateStructure(idNumber);
        LocalDate birthDate = dateofBirthParser.parse(idNumber);

        Gender gender = genderParser.parse(idNumber);

        Citizenship citizenship = citizenshipParser.parse(idNumber);

        int obsoleteDigit = extractDigit(
                idNumber,
                OBSOLETE_DIGIT_INDEX
        );

        int checksumDigit = extractDigit(
                idNumber,
                CHECKSUM_INDEX
        );

        boolean checksumValid = luhnValidator.isValid(idNumber);

        int age = calculateAge(birthDate);

        return new ParsedIdentity(
                idNumber,
                birthDate,
                age,
                gender,
                citizenship,
                obsoleteDigit,
                checksumDigit,
                checksumValid,
                checksumValid
        );
    }

    /**
     * Performs the first validation gate.
     *
     * If the ID does not have exactly 13 numeric digits,
     * none of the parsing operations should proceed
     */
    private void validateStructure(String idNumber){
        if (!regexValidator.isValid(idNumber)) {

            throw new IllegalArgumentException(
                    "Invalid South African Id format. " + "ID must contain exactly 13 digits."
            );
        }
    }

    /**
     * Extracts a numeric digit from a known ID position.
     */
    private int extractDigit(String idNumber, int index){
        return Character.digit(
                idNumber.charAt(index),
                10
        );
    }

    /**
     * Calculates the person's age using the current date
     *
     * Period is used rather than simply subtracting years,
     * because birthday boundaries matter.
     *
     * example:
     * Birth date: 1990-12-20
     * Current date: 2026-08-17
     */
    private int calculateAge(LocalDate birthdate){
        return Period.between(
                birthdate,
                LocalDate.now()
        ).getYears();
    }
}
