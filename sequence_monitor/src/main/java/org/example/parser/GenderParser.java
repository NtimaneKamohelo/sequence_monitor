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

    /**
     * Minimum possible value for the gender sequence.
     */

    private static final int MIN_GENDER_SEQUENCE = 0;

    /**
     * Maximum possible value for the gender sequence.
     */

    private static final int MAX_GENDER_SEQUENCE = 9999;

    /**
     * First sequence value assigned to the male range.
     */

    private static final int MALE_THRESHOLD = 5000;

    /**
     * Extracts and determines the gender from a complete South African ID Number.
     *
     * @param idNumber 13-digits
     */

    public Gender parse(String idNumber) {
        validatedIdNumber(idNumber);
        String genderSequence = idNumber .substring(6,10);
        int genderCode = parseGenderSequence(genderSequence);

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
     * Converts the four-character SSSS into an integer
     */

    private int parseGenderSequence(String genderSequence) {

        try {
            int genderCode = Integer.parseInt(genderSequence);

            if(genderCode < MIN_GENDER_SEQUENCE || genderCode > MAX_GENDER_SEQUENCE) {
                throw new IllegalArgumentException("Gender sequence must be between 0000 and 9999");
            }

            return genderCode;
        }catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Gender sequence must contain numeric characters",
                    exception
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
