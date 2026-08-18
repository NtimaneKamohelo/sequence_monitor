package org.example.validation;

import org.springframework.stereotype.Component;

/**
 * Validates the checksum digit of a South African
 * ID number using Luhn Algorithm
 *
 * The RSA ID Contains:
 * 12 Data Digits + 1 checksum digit
 */
@Component
public class LuhnValidator {
    private static final int ID_LENGTH = 13;

    /**
     * Determines whether the Supplied ID has Valid LUHN checksum
     *
     * @return idNumber 13-digit RSA ID
     * @return true when checksum is valid
     */
    public boolean isValid(String idNumber){
        if(idNumber == null || !idNumber.matches("\\d{13}")){
            return false;
        }

        int sum = 0;

        /**
         * Process the first 12 digits
         *
         * positions 1,3,5,...,11
         * are added directly.
         *
         * positions 2,4,6,...,12
         * are doubled.
         */
        for (int index = 0; index < ID_LENGTH -1; index++) {
            int digit = Character.digit(
                    idNumber.charAt(index),
                    10
            );


            /**
             * Java indexes start at zero
             *
             * Therefore:
             * index 0 -> first digit
             * index 1 -> second digit
             * We double indexes 1,3,5,...,11.
             */
            if (index % 2 == 1) {
                digit *= 2;

                if(digit > 9){
                    digit -= 9;
                }
            }
            sum += digit;
        }

        int checkDigit = Character.digit(
                idNumber.charAt(12),
                10
        );
        int calculatedCheckDigit = (10 - (sum % 10)) % 10;

        return calculatedCheckDigit == checkDigit;
    }
}
