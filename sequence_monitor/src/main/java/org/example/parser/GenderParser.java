package org.example.parser;

import org.example.enums.Gender;

/**
 * Responsible for determining gender from the SSSS
 * sequence contained within a South African ID number
 *
 * The SSSS sequence occupies position 7-10
 * of the Human-readable 13-digit ID.
 *
 * Numeric ranges:
 *
 * 0000 - 4999 -> Female
 * 5000 - 9999 -> Male
 */

public class GenderParser {

    private static final int GENDER_START_INDEX = 6;

    private static final int GENDER_END_INDEX = 10;

    /**
     * First sequence value assigned to the male range.
     */
    private static final int MALE_THRESHOLD = 5000;

    /**
     * Extracts and parses the gender from a complete South African ID Number.
     *
     * @param idNumber 13-digits
     * @return Gender classification
     */

    public Gender parse(String idNumber) {
        validatedIdNumber(idNumber);
        String genderSequence = idNumber.substring(
                GENDER_START_INDEX,
                GENDER_END_INDEX
        );

        int genderCode = Integer.parseInt(genderSequence);

        return  determineGender(genderCode);
    }

    private void validatedIdNumber(String idNumber){
        if (idNumber == null) {
            throw new IllegalArgumentException(
                    "South African ID number cannot be null"
            );
        }

        if (!idNumber.matches("\\d{13}")){
            throw new IllegalArgumentException(
                    "South African ID number must contain exactly 13 digits"
            );
        }
    }


    /**
     * Converts the numeric gender sequence into the applications Gender enum
     */

    private Gender determineGender(int genderCode){
        if (genderCode >= MALE_THRESHOLD){
            return Gender.MALE;
        }

        return Gender.FEMALE;
    }

}
