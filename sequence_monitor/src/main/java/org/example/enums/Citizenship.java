package org.example.enums;

/**
 * Represents the citizenship/residency classification
 * encoded by digit 11 of a South African Identity number
 */

public enum Citizenship {

    /**
     * Code 0/zero.
     *
     * Represents a South African Citizen according to
     * the application's interpretation of the ID structure.
     */
    CITIZEN(0, "South African Citizen"),

    /**
     * Code 1
     *
     * Represents a permanent resident/non-citizen classification
     * according to the application's configured mapping.
     */
    PERMANENT_RESIDENT(1, "Permanent Resident"),

    /**
     * Code 2.
     *
     * Represents the refugee classification supplied by
     * the application's business rules.
     */
    REFUGEE(2, "Recognized Refugee");

    private final int code;
    private final String description;

    Citizenship(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }


}
