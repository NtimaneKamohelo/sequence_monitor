package org.example.parser;

import org.example.enums.Citizenship;
import org.springframework.stereotype.Component;

/**
 * Responsible for determining the citizenship/residency
 * classificationfrom digit 11 of a South African ID number.
 *
 * Position:
 * 13-digit ID
 * YYMMDD SSSS C A Z
 *             ^
 *             Citizenship
 */
@Component
public class CitizenshipParser {

    /**
     * Citizenship code representing a citizen
     */
    private static final int CITIZEN_CODE = 0;

    /**
     * Citizenship code representing a permanent resident
     */
    private static final int PERMANENT_RESIDENT_CODE = 1;

    /**
     * Citizenship code representing a recognized refugee
     */
    private static final int REFUGEE_CODE = 2;

    private static final int CITIZENSHIP_INDEX = 10;


    /**
     * Extracts the citizenship code from a complete
     * South African ID number and maps it to a Citizenship enum
     *
     * @param idNumber 13-digit RSA ID number.
     * @return corresponding Citizenship classification
     * @throws IllegalArgumentException when the ID is invalid or the citizenship code is unsupported.
     */
    public Citizenship parse(String idNumber){
        validatedIdNumber(idNumber);
        char citizenshipCharacter = idNumber.charAt(
                CITIZENSHIP_INDEX
        );

        int citizenshipCode = Character.digit(
                citizenshipCharacter,
                10
        );

        return  determineCitizenship(citizenshipCode);
    }

    /**
     * Performs the structural validation required before
     * extracting the citizenship digit.
     */
    private void validatedIdNumber(String idNumber) {
        if (idNumber == null){
            throw new IllegalArgumentException(
                    "South African ID number cannot be null"
            );
        }

        if (!idNumber.matches("\\d{13}")){
            throw new IllegalArgumentException(
                    "Citizenship code must be numeric"
            );
        }
    }

    /**
     * Converts the citizenship character into an integer

    private int parseCitizenshipCode(char citizenshipCharacter){
        if (!Character.isDigit(citizenshipCharacter)){
            throw new IllegalArgumentException(
                    "Citizenship code must be numeric"
            );
        }
        return Character.digit(citizenshipCharacter,10);
    }*/

    /**
     * maps the numeric citizenship code to the domain enum
     */
    private Citizenship determineCitizenship(int citizenshipCode){

        switch (citizenshipCode){

            case CITIZEN_CODE: return Citizenship.CITIZEN;

            case PERMANENT_RESIDENT_CODE: return Citizenship.PERMANENT_RESIDENT;

            case REFUGEE_CODE: return Citizenship.REFUGEE;

            default:
                throw new IllegalArgumentException(
                        "Unsupported citizenship code: " + citizenshipCode
                );
        }
    }
}
